(ns where-you-at.main
    (:use-macros [dommy.macros :only [node sel1 sel by-id]])
    (:require [dommy.core :as dom]))

(defn log [stuff]
  (.log js/console stuff))

(defn process-location-data [position]
    (let [coords (aget position "coords")
          latitude (aget coords "latitude")
          longitude (aget coords "longitude")]
    (log (str latitude " " longitude))))

(.watchPosition js/navigator.geolocation process-location-data)


