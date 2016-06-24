--------------------- 1.秒杀商品表----------------------
CREATE TABLE seckills (
  id          SERIAL,
  merchat_id  INTEGER REFERENCES merchants,
  name        VARCHAR      NOT NULL,
  logo        VARCHAR,
  describe    VARCHAR,
  integral    INTEGER      NOT NULL,
  examine     BOOL         NOT NULL  DEFAULT FALSE,
  publish     BOOL         NOT NULL  DEFAULT FALSE,
  images      VARCHAR,
  total       INTEGER      NOT NULL,
  remain      INTEGER      NOT NULL,
  start_time  TIMESTAMP(6) NOT NULL,
  end_time    TIMESTAMP(6) NOT NULL,
  create_time TIMESTAMP(6) NOT NULL  DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id)
);
CREATE INDEX ON seckills (start_time);
CREATE INDEX ON seckills (end_time);
CREATE INDEX ON seckills (create_time);


ALTER SEQUENCE "public"."seckills_id_seq" RESTART 1000 OWNED BY "seckills"."id";

-- 初始化数据
INSERT INTO seckills (name, integral, examine,publish, total, remain, start_time, end_time)
VALUES
  ('1000元秒杀iphone6', 10, TRUE ,TRUE, 100, 100, '2016-06-14 00:00:00', '2016-06-15 00:00:00'),
  ('800元秒杀ipad', 10, TRUE ,TRUE, 200, 200, '2016-06-15 00:00:00', '2016-06-16 00:00:00'),
  ('6600元秒杀mac book pro', 10, TRUE ,TRUE, 300, 300, '2016-06-16 00:00:00', '2016-06-17 00:00:00'),
  ('7000元秒杀iMac', 10, TRUE ,TRUE, 400, 400, '2016-06-14 00:00:00', '2016-06-15 00:00:00');



-- 表注释
COMMENT ON TABLE seckills IS '秒杀商品表';
COMMENT ON COLUMN seckills.id IS '秒杀商品的id';
COMMENT ON COLUMN seckills.merchat_id IS '秒杀商品关联的店铺';
COMMENT ON COLUMN seckills.name IS '秒杀商品的名称';
COMMENT ON COLUMN seckills.logo IS '秒杀商品的logo';
COMMENT ON COLUMN seckills.describe IS '秒杀商品的描述';
COMMENT ON COLUMN seckills.integral IS '秒杀商品需要的积分';
COMMENT ON COLUMN seckills.examine IS '秒杀商品是否审查通过';
COMMENT ON COLUMN seckills.publish IS '秒杀商品是否可以秒杀';
COMMENT ON COLUMN seckills.images IS '秒杀商品详情图片,可以多个链接,用英文逗号分开';
COMMENT ON COLUMN seckills.total IS '秒杀商品的总数量';
COMMENT ON COLUMN seckills.remain IS '秒杀商品剩余数量';
COMMENT ON COLUMN seckills.start_time IS '秒杀的开始时间';
COMMENT ON COLUMN seckills.end_time IS '秒杀的结束时间';
COMMENT ON COLUMN seckills.create_time IS '创建时间';

--------------------- 2.秒杀成功明细表 ----------------------
CREATE TABLE success_seckilleds (
  seckill_id        INTEGER      NOT NULL REFERENCES seckills,
  user_id           INTEGER      NOT NULL REFERENCES users,
  state             INT2         NOT NULL DEFAULT -1,
  verification_code VARCHAR(255),
  exchange_time     TIMESTAMP(6),
  seckill_at        TIMESTAMP(6) NOT NULL,
  create_time       TIMESTAMP(6) NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (seckill_id, user_id)
);

CREATE INDEX ON success_seckilleds (user_id);
CREATE INDEX ON success_seckilleds (seckill_id);

COMMENT ON TABLE success_seckilleds IS '秒杀成功明细表';
COMMENT ON COLUMN success_seckilleds.seckill_id IS '秒杀商品的id';
COMMENT ON COLUMN success_seckilleds.user_id IS '用户id';
COMMENT ON COLUMN success_seckilleds.state IS '秒杀状态:  -1:无效,0:成功';
COMMENT ON COLUMN success_seckilleds.verification_code IS '核销码';
COMMENT ON COLUMN success_seckilleds.exchange_time IS '核销时间';
COMMENT ON COLUMN success_seckilleds.seckill_at IS '秒杀时间';
COMMENT ON COLUMN success_seckilleds.create_time IS '创建时间';



