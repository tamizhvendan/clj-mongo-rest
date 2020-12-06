(ns restful.app
  (:require [restful.asset.api :refer [asset-api]]
            [bidi.ring :as bidi-ring]
            [ring.middleware.json :as ring-json]))


(defn routes []
  ["/" (merge (asset-api))])

(defn app []
  (-> (bidi-ring/make-handler (routes))
      (ring-json/wrap-json-body {:keywords? true})
      (ring-json/wrap-json-response)))
