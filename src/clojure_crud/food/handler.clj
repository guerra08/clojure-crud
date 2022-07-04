(ns clojure-crud.food.handler
  (:require [clojure-crud.food.repository :as fr]
            [ring.util.response :as r]))

(defn post-food [{:keys [parameters]}]
  (let [food (:body parameters)
        id (:id (fr/insert-food food))]
    (r/created (str "/food/" id) "Created")))

(defn get-all-foods [_]
  (r/response (fr/get-foods)))

(defn get-food-by-id [req]
  (let [id (:id (:path-params req))
        food (fr/get-by-id id)]
    (if (nil? food)
      (r/not-found {:error (str "Food with id " id " not found")})
      (r/response food))))
