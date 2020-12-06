(ns user
  (:require [restful.infra.core :as infra]
            [clojure.tools.namespace.repl :as repl]))

(defn reset []
  (repl/refresh :after 'infra/start-app))

(defn stop []
  (infra/stop-app))