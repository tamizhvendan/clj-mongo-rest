(ns restful.asset.db
  (:require [restful.infra.db :refer [db]]
            [monger.collection :as mc]
            [monger.operators :as op])
  (:import (org.bson.types ObjectId)))

(defn create [name]
  (mc/insert-and-return db "assets" {:name name}))

(defn find-all []
  (mc/find-maps db "assets"))

(defn find-by-id [id]
  (mc/find-map-by-id db "assets" (ObjectId. id)))

(defn delete-by-id [id]
  (mc/remove db "assets" {:_id (ObjectId. id)}))

(defn rename [id name]
  (mc/update-by-id db "assets" (ObjectId. id) {op/$set
                                               {:name name}}))

(comment
  (create "Mac Book Pro")
  (find-all)
  (find-by-id "5fcc8a93f16299bd6a93fff8")
  (delete-by-id "5fcc8a93f16299bd6a93fff8")
  (rename "5fcc8e71f16299bd6a93fffc" "Mac Book Pro 2015"))
