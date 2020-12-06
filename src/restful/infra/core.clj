(ns restful.infra.core
  (:require [restful.infra.log]
            [restful.infra.db]
            [mount.core :as mount]))

(defn start-app []
  (mount/start))

(defn stop-app []
  (mount/stop))