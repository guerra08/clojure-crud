(ns clojure-crud.routes
  (:require [clojure-crud.food.food :refer [Food]]
            [clojure-crud.food.handler :as fh]))

(def food-routes
  [["/food" {:get  {:handler fh/get-all-foods}
             :post {:parameters {:body Food}
                    :handler    fh/post-food}}]
   ["/food/:id" {:parameters {:path {:id int?}}
                 :get        {:handler fh/get-food-by-id}}]])
