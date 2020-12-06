(ns restful.core
  (:require [restful.infra.log :as log]
            [restful.infra.core :as infra]
            [restful.infra.config :as config])
  (:gen-class))

(.addShutdownHook (Runtime/getRuntime)
                  (Thread. (fn []
                             (infra/stop-app))))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (infra/start-app)
  (log/info (str "app started with mongo connection " (config/mongo-conn-str))))
