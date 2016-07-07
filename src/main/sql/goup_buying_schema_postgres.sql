DROP TABLE success_group_buyings;
DROP TABLE group_buyings;
--------------------- 1.团购商品表----------------------
CREATE TABLE group_buyings(
  id                SERIAL,
  merchant_id        INTEGER      REFERENCES merchants,
  name              VARCHAR      NOT NULL,
  logo              VARCHAR,
  describe          VARCHAR,
  integral          INTEGER      NOT NULL,
  original_integral INTEGER ,
  examine           BOOL ,
  publish           BOOL         NOT NULL  DEFAULT FALSE,
  images            JSON,
  "limit"           INTEGER      ,
  total             INTEGER      NOT NULL,
  remain            INTEGER      NOT NULL,
  details     TEXT  ,
  start_time        TIMESTAMP(6) NOT NULL,
  end_time          TIMESTAMP(6) NOT NULL,
  created_at       TIMESTAMP(6) NOT NULL  DEFAULT current_date,
  updated_at      TIMESTAMP(6) NOT NULL DEFAULT current_date,
  PRIMARY KEY (id)
);

CREATE INDEX ON group_buyings (start_time);
CREATE INDEX ON group_buyings (end_time);
CREATE INDEX ON group_buyings (created_at);


ALTER SEQUENCE "public"."group_buyings_id_seq" RESTART 10000 OWNED BY "group_buyings"."id";

-- 初始化数据
INSERT INTO group_buyings (name,integral,original_integral,examine,publish,"limit",total,remain, start_time, end_time)
VALUES
  ('1000元团购iphone6',10,20,TRUE ,TRUE ,NULL ,100, 100, '2016-06-22 00:00:00', '2016-06-30 00:00:00'),
  ('800元团购ipad',10,20,TRUE ,TRUE , 1,200,200, '2016-06-22 00:00:00', '2016-06-30 00:00:00'),
  ('6600元团购mac book pro',10,20,TRUE ,TRUE,5 ,300, 300, '2016-06-22 00:00:00', '2016-06-30 00:00:00'),
  ('7000元团购iMac',10,20,TRUE ,TRUE , NULL ,400,400, '2016-06-30 00:00:00', '2016-07-15 00:00:00');


-- 表注释
COMMENT ON TABLE group_buyings IS '团购商品表';
COMMENT ON COLUMN group_buyings.id IS '团购商品的id';
COMMENT ON COLUMN group_buyings.merchant_id IS '团购商品关联的店铺';
COMMENT ON COLUMN group_buyings.name IS '团购商品的名称';
COMMENT ON COLUMN group_buyings.logo IS '团购商品的logo';
COMMENT ON COLUMN group_buyings.describe IS '团购商品的描述';
COMMENT ON COLUMN group_buyings.integral IS '团购商品需要的积分';
COMMENT ON COLUMN group_buyings.original_integral IS '团购商品原来所需要的积分';
COMMENT ON COLUMN group_buyings.examine IS '团购商品是否审查通过';
COMMENT ON COLUMN group_buyings.publish IS '团购商品是否可以团购';
COMMENT ON COLUMN group_buyings.images IS '团购商品详情图片,可以多个链接,用英文逗号分开';
COMMENT ON COLUMN group_buyings."limit" IS '团购商品每个人限制购买的数量';
COMMENT ON COLUMN group_buyings.total IS '团购商品的总数量';
COMMENT ON COLUMN group_buyings.remain IS '团购商品剩余数量';
COMMENT ON COLUMN group_buyings.details IS '团购商品的富文本详细描述';
COMMENT ON COLUMN group_buyings.start_time IS '团购的开始时间';
COMMENT ON COLUMN group_buyings.end_time IS '团购的结束时间';
COMMENT ON COLUMN group_buyings.created_at IS '创建时间';
COMMENT ON COLUMN group_buyings.updated_at IS '更新时间';



--------------------- 2.团购成功明细表 ----------------------
CREATE TABLE success_group_buyings (
  id SERIAL,
  group_buying_id  INTEGER      NOT NULL REFERENCES group_buyings,
  user_id     INTEGER      NOT NULL REFERENCES users,
  state       INT2         NOT NULL DEFAULT -1,
  verification_code VARCHAR(255) ,
  exchange_time TIMESTAMP(6) ,
  group_buying_at  TIMESTAMP(6) NOT NULL ,
  account_id      INTEGER  REFERENCES merchant_accounts,
  created_at TIMESTAMP(6) NOT NULL DEFAULT current_date,
  updated_at      TIMESTAMP(6) NOT NULL DEFAULT current_date,
  PRIMARY KEY (id)
);

ALTER SEQUENCE "public"."success_group_buyings_id_seq" RESTART 1000000000 OWNED BY "success_group_buyings"."id";


CREATE INDEX ON success_group_buyings (user_id);
CREATE INDEX ON success_group_buyings (group_buying_id);

COMMENT ON TABLE success_group_buyings IS '团购成功明细表';
COMMENT ON COLUMN success_group_buyings.id IS '团购成功记录id';
COMMENT ON COLUMN success_group_buyings.group_buying_id IS '团购商品的id';
COMMENT ON COLUMN success_group_buyings.user_id IS '用户id';
COMMENT ON COLUMN success_group_buyings.state IS '团购状态:  -1:无效,0:成功';
COMMENT ON COLUMN success_group_buyings.verification_code IS '核销码';
COMMENT ON COLUMN success_group_buyings.exchange_time IS '核销时间';
COMMENT ON COLUMN success_group_buyings.group_buying_at IS '团购时间';
COMMENT ON COLUMN success_group_buyings.account_id IS '处理核销账户的id';
COMMENT ON COLUMN success_group_buyings.created_at IS '创建时间';
COMMENT ON COLUMN success_group_buyings.updated_at IS '更新时间';




