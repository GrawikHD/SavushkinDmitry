/*1. Найдите номер модели, скорость и размер жесткого диска для всех ПК стоимостью менее 500 дол. Вывести: model, speed и hd*/
SELECT model, speed, hd FROM PC 
WHERE price < 500

/*2. Найдите производителей принтеров. Вывести: maker*/
SELECT maker FROM Product
WHERE type='Printer'
GROUP BY maker

/*3. Найдите номер модели, объем памяти и размеры экранов ПК-блокнотов, цена которых превышает 1000 дол.*/
SELECT model, ram, screen FROM Laptop
WHERE price > 1000

/*4. Найдите все записи таблицы Printer для цветных принтеров.*/
SELECT * FROM Printer
WHERE color='y'

/*5. Найдите номер модели, скорость и размер жесткого диска ПК, имеющих 12x или 24x CD и цену менее 600 дол.*/
SELECT model, speed, hd FROM PC
WHERE cd IN ('12x', '24x') AND price<600

/*6. Для каждого производителя, выпускающего ПК-блокноты c объёмом жесткого диска не менее 10 Гбайт,
найти скорости таких ПК-блокнотов. Вывод: производитель, скорость.*/
SELECT DISTINCT maker, speed FROM Product
INNER JOIN
Laptop ON product.model=Laptop.model
WHERE hd>=10

/*7. Найдите номера моделей и цены всех имеющихся в продаже продуктов (любого типа) производителя B (латинская буква).*/
SELECT t1.model, price FROM Product
JOIN (
(SELECT PC.model, PC.price FROM PC)
UNION
(SELECT Laptop.model, Laptop.price FROM Laptop)
UNION
(SELECT Printer.model, Printer.price FROM Printer)
) as t1
ON Product.model=t1.model
WHERE Product.maker='B'

/*8. Найдите производителя, выпускающего ПК, но не ПК-блокноты.*/
SELECT maker FROM product
WHERE type='PC'

EXCEPT

SELECT maker FROM product
WHERE type='Laptop'

/*9. Найдите производителей ПК с процессором не менее 450 Мгц. Вывести: Maker*/
SELECT DISTINCT maker FROM Product
INNER JOIN
PC ON Product.model=PC.model
WHERE PC.speed >= 450

/*10. Найдите модели принтеров, имеющих самую высокую цену. Вывести: model, price*/
SELECT model, price FROM Printer
WHERE price=(SELECT MAX(price) FROM Printer)

/*11. Найдите среднюю скорость ПК.*/
SELECT AVG(speed) FROM PC

/*12. Найдите среднюю скорость ПК-блокнотов, цена которых превышает 1000 дол.*/
SELECT AVG(speed) FROM Laptop
WHERE price>1000

/*13. Найдите среднюю скорость ПК, выпущенных производителем A.*/
SELECT AVG(speed) AS Avg_speed FROM PC
INNER JOIN
Product ON PC.model=Product.model
WHERE Product.maker='A'

/*14. Найдите класс, имя и страну для кораблей из таблицы Ships, имеющих не менее 10 орудий.*/
SELECT Ships.class, Ships.name, country FROM Ships
INNER JOIN
Classes ON Ships.class=Classes.class
WHERE numGuns>=10

/*15. Найдите размеры жестких дисков, совпадающих у двух и более PC. Вывести: HD*/
SELECT hd FROM PC
GROUP BY hd
HAVING count(*)>=2

/*16. Найдите пары моделей PC, имеющих одинаковые скорость и RAM. В результате каждая пара указывается только один раз,
 т.е. (i,j), но не (j,i), Порядок вывода: модель с большим номером, модель с меньшим номером, скорость и RAM.*/
SELECT MAX(p1.model) as model1, MIN(p2.model) as model2, p1.speed, p1.ram FROM PC p1, PC p2
WHERE p1.speed=p2.speed AND p1.ram=p2.ram
GROUP BY p1.speed, p1.ram
HAVING MAX(p1.model) > MIN(p2.model)
ORDER BY p1.ram DESC

/*17. Найдите модели ПК-блокнотов, скорость которых меньше скорости каждого из ПК.
      Вывести: type, model, speed*/
SELECT DISTINCT pd.type, pd.model, lp.speed FROM Product pd
INNER JOIN
Laptop lp ON pd.model=lp.model
WHERE pd.type='Laptop' AND lp.speed < ALL (SELECT speed FROM PC)

/*18. Найдите производителей самых дешевых цветных принтеров. Вывести: maker, price*/
SELECT p.maker, pr.price FROM Product p
INNER JOIN
Printer pr ON p.model=pr.model
WHERE pr.color='y' AND pr.price=(SELECT MIN(price) FROM Printer WHERE color='y')

/*19. Для каждого производителя, имеющего модели в таблице Laptop, найдите средний размер экрана выпускаемых им ПК-блокнотов.
      Вывести: maker, средний размер экрана.*/
SELECT p.maker, AVG(lp.screen) FROM Product p
INNER JOIN
Laptop lp ON lp.model=p.model
GROUP BY p.maker

/*20. Найдите производителей, выпускающих по меньшей мере три различных модели ПК. Вывести: Maker, число моделей ПК.*/
SELECT maker, count(model) as Count_Model FROM Product
WHERE type='PC'
GROUP BY maker
HAVING count(model)>=3

/*21. Найдите максимальную цену ПК, выпускаемых каждым производителем, у которого есть модели в таблице PC.
      Вывести: maker, максимальная цена.*/
SELECT p.maker, MAX(pc.price) FROM PC pc
INNER JOIN
Product p ON p.model=pc.model
GROUP BY p.maker