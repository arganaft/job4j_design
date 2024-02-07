
create table players
(
    id serial primary key,
    player_level int,
    gold int,
    items int,
    unit_name text
);

insert into players (player_level, gold, items, unit_name)
VALUES (3, 56, 5, 'Nimm');
insert into players (player_level, gold, items, unit_name)
VALUES (6, 96, 17, 'Tan');
insert into players (player_level, gold, items, unit_name)
VALUES (4, 34, 12, 'Trix');
insert into players (player_level, gold, items, unit_name)
VALUES (1, 500, 22, 'Bankir');
-- первое действие в первом окне
begin transaction;
    SET TRANSACTION ISOLATION LEVEL SERIALIZABLE;
    UPDATE players
    SET gold = gold - 8,
        items = items + 4
    WHERE unit_name = 'Nimm';
----------
-- третье действие в первом окне
    UPDATE players
    SET gold = gold + 8,
        items = items - 4
    WHERE unit_name = 'Tan';
----------
rollback;

-- второе действие во втором окне
begin transaction;
    SET TRANSACTION ISOLATION LEVEL SERIALIZABLE;
    UPDATE players
    SET gold = gold + 4,
        items = items - 2
    WHERE unit_name = 'Tan';
----------
-- четвертое действие во втором окне
    UPDATE players
    SET gold = gold - 4,
        items = items + 2
    WHERE unit_name = 'Nimm';
----------
commit;


--транзакция которая откатывает или сохраняет изменения при заданном условии.
--DO $$
--BEGIN
--    SET TRANSACTION ISOLATION LEVEL READ COMMITTED;
--
--    UPDATE players
--    SET gold = gold + 12,
--        items = items - 6
--    WHERE unit_name = 'Nimm';
--
--    UPDATE players
--    SET gold = gold - 12,
--        items = items + 6
--    WHERE unit_name = 'Tan';
--
--    IF (SELECT COUNT(*) FROM players WHERE gold < 0 OR items < 0) > 0 THEN
--        ROLLBACK;
--    ELSE
--        COMMIT;
--    END IF;
--END $$;


