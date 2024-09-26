/*1. Вывести список самолетов с кодами 320, 321, 733*/
SELECT * FROM LANIT.AIRCRAFTS_DATA ad 
WHERE ad.AIRCRAFT_CODE IN ('320', '321', '733')

/*2. Вывести список самолетов с кодом не на 3*/
SELECT * FROM LANIT.AIRCRAFTS_DATA ad
WHERE ad.AIRCRAFT_CODE NOT LIKE '3%'

/*3. Найти билеты оформленные на имя «OLGA», с емайлом «OLGA» или без емайла*/
SELECT DISTINCT t.TICKET_NO, t.BOOK_REF, t.PASSENGER_NAME
, CASE WHEN t.EMAIL LIKE 'olga%' THEN t.EMAIL
ELSE null
END CONTACT
FROM LANIT.TICKETS t
WHERE t.PASSENGER_NAME LIKE 'OLGA%'

/*4. Найти самолеты с дальностью полета 5600, 5700. Отсортировать список по убыванию дальности полета*/
SELECT * FROM LANIT.AIRCRAFTS_DATA ad
WHERE ad."RANGE" BETWEEN 5600 AND 5700
ORDER BY ad."RANGE" DESC 

/*5. Найти аэропорты в Moscow. Вывести название аэропорта вместе с городом. Отсортировать по полученному названию*/
SELECT ad.AIRPORT_NAME, ad.CITY FROM LANIT.AIRPORTS_DATA ad 
WHERE ad.CITY='Moscow'
ORDER BY ad.AIRPORT_NAME

/*6. Вывести список всех городов без повторов в зоне «Europe»*/
SELECT DISTINCT ad.CITY, ad.TIMEZONE FROM LANIT.AIRPORTS_DATA ad
WHERE ad.TIMEZONE LIKE 'Eu%'

/*7. Найти бронирование с кодом на «3A4» и вывести сумму брони со скидкой 10%*/
SELECT b.BOOK_REF, b.TOTAL_AMOUNT, (b.TOTAL_AMOUNT - ((b.TOTAL_AMOUNT/100)*10)) procent FROM LANIT.BOOKINGS b
WHERE b.BOOK_REF LIKE '3A4%'
ORDER BY b.TOTAL_AMOUNT DESC

/*8. Вывести все данные по местам в самолете с кодом 320 и классом «Business» строками вида «Данные по месту: номер места 1», «Данные по месту: номер места 2» … и тд*/
SELECT STR FROM LANIT.SEATS s 
WHERE s.AIRCRAFT_CODE='320' AND s.FARE_CONDITIONS='Business'

/*9. Найти максимальную и минимальную сумму бронирования в 2017 году*/
SELECT MAX(b.TOTAL_AMOUNT) AS MaxSumBookings, MIN(b.TOTAL_AMOUNT) AS MinSumBookings
FROM LANIT.BOOKINGS b
WHERE TO_CHAR(b.BOOK_DATE, 'yyyy') = '2017'

/*10. Найти количество мест во всех самолетах, вывести в разрезе самолетов*/
SELECT s.AIRCRAFT_CODE, ad.MODEL, COUNT(s.SEAT_NO) FROM LANIT.SEATS s
LEFT OUTER JOIN 
LANIT.AIRCRAFTS_DATA ad ON s.AIRCRAFT_CODE=ad.AIRCRAFT_CODE
GROUP BY ad.MODEL, s.AIRCRAFT_CODE

/*11. Найти количество мест во всех самолетах с учетом типа места, вывести в разрезе самолетов и типа мест*/
SELECT s.AIRCRAFT_CODE, s.FARE_CONDITIONS, COUNT(s.SEAT_NO) FROM LANIT.SEATS s
GROUP BY s.AIRCRAFT_CODE, s.FARE_CONDITIONS
ORDER BY s.AIRCRAFT_CODE ASC

/*12. Найти количество билетов пассажира ALEKSANDR STEPANOV, телефон которого заканчивается на 11*/
SELECT COUNT(t.BOOK_REF) AS countT FROM LANIT.TICKETS t
WHERE t.PASSENGER_NAME='ALEKSANDR STEPANOV' AND t.PHONE LIKE '%11'

/*13. Вывести всех пассажиров с именем ALEKSANDR, у которых количество билетов больше 2000. Отсортировать по убыванию количества билетов*/
SELECT COUNT(*) AS countT, t.PASSENGER_NAME FROM LANIT.TICKETS t
GROUP BY t.PASSENGER_NAME
HAVING t.PASSENGER_NAME LIKE 'ALEKSANDR%' AND count(*)>2000
ORDER BY COUNT(*) DESC

/*14. Вывести дни в сентябре 2017 с количеством рейсов больше 500*/
SELECT trunc(f.DATE_DEPARTURE), count(*) FROM LANIT.FLIGHTS f
GROUP BY trunc(f.DATE_DEPARTURE)
HAVING trunc(f.DATE_DEPARTURE) BETWEEN to_date('2017.09.01','yyyy.mm.dd') AND TO_DATE('2017.09.30', 'yyyy.mm.dd') 
AND count(*)>500

/*15. Вывести список городов, в которых несколько аэропортов*/
SELECT ad.CITY, count(ad.AIRPORT_NAME) FROM LANIT.AIRPORTS_DATA ad
GROUP BY ad.CITY

/*16. Вывести модель самолета и список мест в нем, т.е. на самолет одна строка результата*/
SELECT DISTINCT ad.MODEL, LISTAGG(s.SEAT_NO || ', ') WITHIN GROUP (ORDER BY s.SEAT_NO)
FROM LANIT.AIRCRAFTS_DATA ad
RIGHT OUTER JOIN
LANIT.SEATS s 
ON s.AIRCRAFT_CODE = ad.AIRCRAFT_CODE
GROUP BY ad.MODEL

/*17. Вывести информацию по всем рейсам из аэропортов в г.Москва за сентябрь 2017*/
SELECT DISTINCT ad2.CITY, ad2.AIRPORT_NAME, f2.SCHEDULED_DEPARTURE, f2.SCHEDULED_ARRIVAL, f2.STATUS FROM LANIT.AIRPORTS_DATA ad2
LEFT OUTER JOIN
LANIT.FLIGHTS f2 ON f2.DEPARTURE_AIRPORT=ad2.AIRPORT_CODE
WHERE TO_CHAR(f2.DATE_DEPARTURE, 'yyyy.mm')='2017.09' AND ad2.CITY='Moscow'

/*18. Вывести кол-во рейсов по каждому аэропорту в г.Москва за 2017*/
SELECT ad.AIRPORT_NAME, COUNT(f.FLIGHT_NO) FROM LANIT.FLIGHTS f
LEFT OUTER JOIN
LANIT.AIRPORTS_DATA ad ON f.DEPARTURE_AIRPORT=ad.AIRPORT_CODE
WHERE ad.CITY='Moscow' AND TO_CHAR(f.DATE_DEPARTURE, 'yyyy')='2017' 
GROUP BY ad.AIRPORT_NAME

/*19. Вывести кол-во рейсов по каждому аэропорту, месяцу в г.Москва за 2017*/
SELECT ad.AIRPORT_NAME, TO_CHAR(f.DATE_DEPARTURE, 'mm'), COUNT(f.FLIGHT_NO) FROM LANIT.FLIGHTS f
LEFT OUTER JOIN
LANIT.AIRPORTS_DATA ad ON f.DEPARTURE_AIRPORT=ad.AIRPORT_CODE
WHERE ad.CITY='Moscow' AND TO_CHAR(f.DATE_DEPARTURE, 'yyyy')='2017' 
GROUP BY ad.AIRPORT_NAME, TO_CHAR(f.DATE_DEPARTURE, 'mm')
ORDER BY TO_CHAR(f.DATE_DEPARTURE, 'mm')

/*20. Найти все билеты по бронированию на «3A4B»*/
SELECT t.* FROM LANIT.TICKETS t 
LEFT OUTER JOIN
LANIT.BOOKINGS b ON t.BOOK_REF=b.BOOK_REF
WHERE b.BOOK_REF LIKE '3A4B%'
/*21. Найти все перелеты по бронированию на «3A4B»*/
SELECT DISTINCT tf.TICKET_NO, tf.FLIGHT_ID, t2.BOOK_REF, t2.PASSENGER_NAME, t2.CONTACT_DATA FROM LANIT.TICKETS t2 
RIGHT OUTER JOIN
LANIT.TICKET_FLIGHTS tf ON t2.TICKET_NO=tf.TICKET_NO
WHERE t2.BOOK_REF LIKE '3A4B%'






