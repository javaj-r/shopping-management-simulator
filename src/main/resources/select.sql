WITH RECURSIVE q AS (
    SELECT c.id, c.name, c.parent_id
    FROM category c
    WHERE id = 1
    UNION ALL
    SELECT c.id, c.name, c.parent_id
    FROM category c
             JOIN q ON c.parent_ID = q.id
)
SELECT q.id qid, q.name qname, q.parent_id, p.id pid, p.price, p.stock
FROM q
         JOIN product p ON q.id = p.category_id;



INSERT INTO admin(username, password)
SELECT 'admin', 'admin'
where not exists(
        select * from admin where username = 'admin');