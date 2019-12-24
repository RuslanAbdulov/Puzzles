create table if not exists ad_loy(
    cid bigint(20),
    first_name varchar(50), -- 150 in file
    last_name varchar(50), -- 150 in file
    line1 varchar(400),
    line2 varchar(400),
    city varchar(200),
    state varchar(200),
    zip varchar(100),
    country varchar(200),
    birth_date date,
    mobile_number varchar(25),
    gender tinyint(1), -- not nvarchar2(10)
    loyalty_number varchar(255), -- to big, CHAR(20) in file
    email_address varchar(150),
    valid_address tinyint(1),
    record_type char(1),
    valid_email_address tinyint(1),
    cell_double_opt_in tinyint(1),
    employee_status tinyint(1),
    store_number bigint(10),
    language_preference varchar(20),
    enrollment_date date,
    enrollment_source bigint(10),
    enrollment_id varchar(20),
    aecc_status tinyint(1),
    aecc_open_date date,
    aecc_close_date date,
    aevisa_status tinyint(1),
    aevisa_account_key varchar(80),
    aevisa_open_date date,
    aevisa_close_date date,

    CONSTRAINT ad_loy_pk PRIMARY KEY (cid)
);




/*
HHKEY AS CHAR(20)
FNAME AS CHAR(150)
LNAME AS CHAR(150)
STREET ADDRESS" AS CHAR(400)
2ndry" AS CHAR(400)
CITY" AS CHAR(200)
ST" AS CHAR(200)
Zip" AS CHAR(100)
CTRY" AS CHAR(200)
BDAY" AS CHAR(8)
PHONE" AS CHAR(25)
GENDER" AS CHAR(1)
LOYALTY" AS CHAR(20)
EMAIL" AS CHAR(150)
ADDR_VALID" AS CHAR(1)
RecordType" AS CHAR(1)
EMAIL_VALIDATION" AS CHAR(1)
CELL_DOUBLEOPT" AS CHAR(1)
EMPLOYEE_STATUS" AS CHAR(1)
STORENUMBER" AS CHAR(10)
LANG_PREF" AS CHAR(5)
ENROLLMENT_DT" AS CHAR(8)
ENROLLMENT_SRC" AS CHAR(30)
ENROLLMENT_ID" AS CHAR(20)
AECC_STATUS" AS CHAR(1)
AECC_OPEN_DT" AS CHAR(8)
AECC_CLOSE_DT" AS CHAR(8)
AEVISA_STATUS" AS CHAR(1)
AEVISA_ACCT_KEY" AS CHAR(20)
AEVISA_OPEN_DT" AS CHAR(8)
AEVISA_CLOSE_DT" AS CHAR(8)


0000000000010|MICHELLE|ALANIZ|274 EBONY CT||RIO GRANDE CITY|TX|785829644|US|01011991||2|70007209283732|mich_alz09@yahoo.com|1|S||0||684|0|04182006|19||||||||
0000000000671|HEATHER|ARTH|743 E YERBY ST||MARSHALL|MO|653402351|US|02091957||2|76548774938051|G.ARTH@SBCGLOBAL.NET|1|S||0||2097|0|07292008|14||2|09132007|06132011|1|0009327534599|07162008|
 */



