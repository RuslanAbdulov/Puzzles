-- Rewards (AE to B+P)

create table if not exists reward(
    -- is synthetic PK needed ?
    member_reward_id bigint(20),
    auth_cd char(3) not null,
    suc_cd char(20) not null,
    campaign_exp_date date not null,

    PRIMARY KEY (member_reward_id)
);
