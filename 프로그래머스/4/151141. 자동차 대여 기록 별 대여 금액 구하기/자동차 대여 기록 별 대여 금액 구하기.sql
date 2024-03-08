/*
<자동차 대여 기록 별 대여 금액 구하기>

1. 테이블 : CAR_RENTAL_COMPANY_CAR C
           CAR_RENTAL_COMPANY_RENTAL_HISTORY H
           CAR_RENTAL_COMPANY_DISCOUNT_PLAN P

2. 객체
 : car_type
    , car_id
    , history_id
    , start_date
    , end_date
    , duration_type
    , discount_rate
    , daily fee
           
3. 출력값
    history_id
    , Fee

4. 조건
 : Fee는 정수만 출력
  , C.car_type = '트럭'
  , FEE DESC
  , H.history_id DESC
  
--=======================================
-- Fee(정수)
Fee = 대여기간 * C.daily_fee(트럭 가격) * (duration_type에 해당하는 discount_rate)
   (★ 7일 미만일 경우 ▶ Fee = 대여기간 * C.daily_fee(트럭가격))
(duration = end_date - start_date +1) duration
(duration_type에 해당하는 discount_rate)
        = duration_type에 값이 있으면 ▶ discount_rate,
          duration_type에 값이 없으면 ▶ 0 (∵ 7일 미만이면 할인없음)
        = (1 - NVL(P.discount_rate, 0) / 100)

-- FEE 결과
-- 정수로 출력할 것
TRUNC(duration * daily_fee * (1 - NVL(P.discount_rate, 0) / 100)) as FEE

-- duration 구하기
SELECT history_id,
       H.END_DATE - H.START_DATE + 1 AS DURATION
    FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY H
        , CAR_RENTAL_COMPANY_CAR C
    WHERE C.CAR_TYPE = '트럭';

-- duration_type 구하기
SELECT history_id
        ,CASE WHEN H.END_DATE - H.START_DATE + 1 BETWEEN 7 AND 29 
            THEN '7일 이상'
        WHEN H.END_DATE - H.START_DATE + 1 BETWEEN 30 AND 89 
            THEN '30일 이상'
        WHEN H.END_DATE - H.START_DATE + 1 >= 90 
            THEN '90일 이상'
        END AS DURATION_TYPE
    FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY H
        , CAR_RENTAL_COMPANY_CAR C
    WHERE C.CAR_TYPE = '트럭';
    
-- discount_rate 구하기
-- duration_type에 해당하는 discount_rate를 출력
-- car_type = '트럭'
SELECT T.history_id
    , (1 - NVL(P.discount_rate, 0) / 100) as discount_rate
    FROM (SELECT H.HISTORY_ID
                ,    CASE WHEN H.END_DATE - H.START_DATE + 1 BETWEEN 7 AND 29 
                        THEN '7일 이상'
                    WHEN H.END_DATE - H.START_DATE + 1 BETWEEN 30 AND 89 
                        THEN '30일 이상'
                    WHEN H.END_DATE - H.START_DATE + 1 >= 90 
                        THEN '90일 이상'
                    END AS DURATION_TYPE
            FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY H
                , CAR_RENTAL_COMPANY_CAR C
            WHERE C.CAR_TYPE = '트럭'
                AND C.CAR_ID = H.CAR_ID
          ) T
          ,CAR_RENTAL_COMPANY_DISCOUNT_PLAN P
;

-- daily_fee 구하기
-- CAR_ID, 트럭인 DAILY_FEE
SELECT CAR_ID, CAR_TYPE, DAILY_FEE
    FROM CAR_RENTAL_COMPANY_CAR
    WHERE CAR_TYPE = '트럭'
;

-- HISTORY_ID, FEE 구하기
-- FEE = TRUNC(duration * daily_fee
--        * (1 - NVL(P.discount_rate, 0) / 100)) as FEE
-- CAR_TYPE = '트럭'

-- [외부조인]
--  : 조인조건에서 데이터가 없는 테이블의 컬럼에 (+) 기호를 붙임
*/

-- 도출한 정답
SELECT history_id
        , TRUNC(duration * daily_fee
            * (1 - NVL(P.discount_rate, 0)/100)) AS fee
    FROM ( SELECT H.history_id
                , C.car_type
                , C.daily_fee
                , H.end_date - H.start_date +1 AS duration
                ,   CASE WHEN H.end_date - H.start_date + 1
                        BETWEEN 7 AND 29
                        THEN '7일 이상'
                    WHEN H.end_date - H.start_date + 1
                        BETWEEN 30 AND 89
                        THEN '30일 이상'
                    WHEN H.end_date - H.start_date + 1 >= 90
                        THEN '90일 이상'
                    END AS duration_type
                FROM CAR_RENTAL_COMPANY_CAR C
                    , CAR_RENTAL_COMPANY_RENTAL_HISTORY H
                WHERE C.car_id = H.car_id
                    AND C.car_type = '트럭'
            ) T
            , CAR_RENTAL_COMPANY_DISCOUNT_PLAN P
    WHERE T.car_type = P.car_type(+) --외부조인
        AND T.duration_type = P.duration_type(+) -- 외부조인
    ORDER BY fee DESC, T.history_id DESC
;