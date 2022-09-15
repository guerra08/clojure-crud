(ns clojure-crud.infra.database
  (:require [next.jdbc :as jdbc]))

(def db {:dbtype "h2" :dbname "foodyworld"})

(def ds (jdbc/get-datasource db))

(comment (jdbc/execute! ds ["
create table if not exists food (
  id int auto_increment primary key,
  name varchar(50),
  country varchar(80)
)"]))

(comment (jdbc/execute! ds ["
drop table if exists food;
"]))

(comment (jdbc/execute! ds ["
delete from food;
"]))