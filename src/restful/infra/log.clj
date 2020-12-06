(ns restful.infra.log
  (:require [mount.core :as mount])
  (:import [java.util.logging Logger]))

(mount/defstate logger
                :start (Logger/getLogger "clj-mongo-rest"))

(defn info [msg]
  (.info logger msg))

(defn fatal [msg]
  (.severe logger msg)
  (System/exit 1))
