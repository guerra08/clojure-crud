(ns clojure-crud.food.repository
  (:require [clojure-crud.persistence.database :refer [ds]]
            [next.jdbc :as j]
            [next.jdbc.result-set :as rs]
            [next.jdbc.sql :as s]))

(defn insert-food
  "Adds food into the database"
  [food]
  (s/insert! ds :food food
             {:builder-fn rs/as-unqualified-lower-maps}))

(defn get-foods
  "Gets all foods"
  []
  (j/execute! ds
              ["SELECT * FROM food;"]
              {:builder-fn rs/as-unqualified-lower-maps}))

(defn get-by-id
  "Get by id"
  [id]
  (s/get-by-id ds :food id
               {:builder-fn rs/as-unqualified-lower-maps}))

(defn delete-by-id 
  "Deletes by id"
  [id]
  (s/delete! ds :food {:id id}))
