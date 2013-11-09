(ns where-you-at.main
    (:use-macros [dommy.macros :only [node sel1 sel by-id]])
    (:require [dommy.core :as dom]))

(def *connected* false)
(def *peer* (js/Peer. (js-obj "key" "0uudvj3cp4fe0zfr"
                            "id" "test1")))
(def *connection* (.connect *peer* "test2"))

(defn log [stuff]
  (.log js/console stuff))

(defn send-location-data [position]
    (let [coords (aget position "coords")
          latitude (aget coords "latitude")
          longitude (aget coords "longitude")]
      (when *connected*
        (.send *connection* (str latitude " " longitude)))))

(defn process-recieved-data [data]
  (let [words (clojure.string/split data #" ")]))

(.watchPosition js/navigator.geolocation send-location-data)




(defn toggle-connected []
  (set! *connected* (not *connected*)))


(.on *connection* "open" toggle-connected)

