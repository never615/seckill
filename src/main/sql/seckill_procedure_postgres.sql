-- 定义存储过程

-- 1. 插入秒杀明细   2.减少库存  3.减少用户积分

-- row_count：0未修改数据，>0表示修改的行数，<0表示SQL错误或者未执行修改SQL
-- result 8002:(秒杀失败)已经秒杀过
-- 8003:(秒杀失败)sql异常
-- 8001:秒杀失败(不在秒杀时间或者库存不足,或未审核或未发布)
-- 8005: 扣减用户积分失败
-- 0:秒杀成功
CREATE FUNCTION execute_seckill(seckill_id        INTEGER, user_id INTEGER, seckill_at TIMESTAMP WITH TIME ZONE,
                                verification_code VARCHAR(255),
  OUT                           result            INTEGER)
AS $$
DECLARE insert_count     INTEGER :=-1;
        seckill_integral INTEGER :=999999999;
BEGIN
  INSERT INTO success_seckilleds (seckill_id, user_id, state, seckill_at, verification_code)
  VALUES (seckill_id, user_id, 0, seckill_at, verification_code);
  GET DIAGNOSTICS insert_count = ROW_COUNT;
  RAISE NOTICE '1. insert_count is %', insert_count;
  IF (insert_count > 0)
  THEN
    UPDATE seckills
    SET remain = remain - 1
    WHERE id = seckill_id
          AND end_time > seckill_at
          AND seckills.start_time < seckill_at
          AND seckills.remain > 0
          AND seckills.examine = TRUE
          AND seckills.publish = TRUE;
    GET DIAGNOSTICS insert_count = ROW_COUNT;
    RAISE NOTICE '2. insert_count is %', insert_count;
    IF (insert_count > 0)
    THEN
      SELECT integral
      INTO seckill_integral
      FROM seckills
      WHERE id = seckill_id;

      UPDATE users
      SET integral = integral - seckill_integral
      WHERE id = user_id
            AND integral > seckill_integral;
      GET DIAGNOSTICS insert_count = ROW_COUNT;
      IF (insert_count > 0)
      THEN
        result:=0;
      ELSE
        result:=8005;
      END IF;
    ELSEIF (insert_count = 0)
      THEN
        result:= 8001;
    ELSE
      result:=8003;
    END IF;
  ELSEIF (insert_count = 0)
    THEN
      result:=8002;
  ELSE
    result:=8003;
  END IF;
END;
$$ LANGUAGE plpgsql;

--存储过程定义结束

