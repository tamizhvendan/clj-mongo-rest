(ns restful.asset.api
  (:require [restful.asset.db :as asset]
            [ring.util.response :as ring]))


(defn create-asset-handler [req]
  (let [new-asset (asset/create (get-in req [:body :name]))]
    (ring/response new-asset)))

(defn get-all-assets-handler [req]
  (ring/response (asset/find-all)))

(defn delete-asset-handler [{:keys [route-params]}]
  (asset/delete-by-id (:id route-params))
  (ring/response {}))

(defn get-asset-handler [{:keys [route-params]}]
  (if-let [x (asset/find-by-id (:id route-params))]
    (ring/response x)
    (ring/not-found {})))

(defn rename-asset-handler [{:keys [route-params body]}]
  (asset/rename (:id route-params) (:name body))
  (ring/response {}))

(defn asset-api []
  {"assets" {:post create-asset-handler
             :get get-all-assets-handler
             ["/" :id] {:delete delete-asset-handler
                        :get get-asset-handler
                        :put rename-asset-handler}}})

; curl -X POST -d '{"name" : "Dell XPS"}' -H 'Content-Type: application/json' http://localhost:8080/assets
; curl -X PUT -d '{"name" : "Dell XPS 2020"}' -H 'Content-Type: application/json' http://localhost:8080/assets/5fcc9aedf16299c6bfca7958
; curl http://localhost:8080/assets
; curl http://localhost:8080/assets/5fcc99a6f16299c6bfca7954 -X DELETE
; curl http://localhost:8080/assets/5fcc9aedf16299c6bfca7958