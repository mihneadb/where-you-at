(ns where-you-at.main
    (:use-macros [dommy.macros :only [node sel1 sel by-id]])
    (:require [dommy.core :as dom]))

(def *connected* false)
<<<<<<< HEAD
(def *peer* nil)
(def *connection* nil)

(def *peer* (js/Peer. (js-obj "key" "0uudvj3cp4fe0zfr"
                            "id" "test1")))
(def *connection* (.connect *peer* "test2"))
(def *mapInstance* nil)

(defn log [stuff]
  (.log js/console stuff))

(defn send-location-data [position]
    (let [coords (aget position "coords")
          latitude (aget coords "latitude")
          longitude (aget coords "longitude")
          latLng (js/window.google.maps.LatLng. latitude longitude)]
      (center-map latLng)
      (place-marker latLng)
      (log latLng)
      (when *connected*
        (log "sending")
        (.send *connection* (str latitude " " longitude)))))

(defn ^:export process-received-data [data]
  (log "got data")
  (log data)
  (let [words (clojure.string/split data #" ")
        latitude (read-string (data 0))
        longitude (read-string (data 1))]
    (log (str "Received" latitude " " longitude))))

(defn ^:export toggle-connected []
  (log "toggled")

(defn place-marker [latLng]
  (google.maps.Marker. (js-obj "position" latLng
                              "map" *mapInstance*
                              "title" "Where you at")))

(defn process-recieved-data [data]
  (let [words (clojure.string/split data #" ")]))


(defn toggle-connected []
>>>>>>> Add marker to current location
  (set! *connected* (not *connected*)))

(defn center-map [latLng]
  (log *mapInstance*)
  (.setCenter *mapInstance* latLng))

(defn map-config-obj []
  (js-obj "zoom" 12
          "mapTypeId" window/google.maps.MapTypeId.ROADMAP
          "center" (js/window.google.maps.LatLng. 25 25)))
;;
(defn ^:export init []
  (.watchPosition js/navigator.geolocation send-location-data)
  (window/google.maps.Map. (sel1 :#map) (map-config-obj)))
  (set! *mapInstance* (window/google.maps.Map. (sel1 :#map) (map-config-obj))))

(set! (.-onload js/window) init)

(defn ^:export accept-connection [conn]
  (.on conn "data" process-received-data))

(defn main []
  (let [own-id (dom/value (sel1 :#self))
        other-id (dom/value (sel1 :#other))]
    (set! *peer* (js/Peer. own-id (js-obj "key" "0uudvj3cp4fe0zfr")))
    (set! *connection* (.connect *peer* other-id))
    (.on *connection* "open" toggle-connected)

    (.on *peer* "connection" accept-connection)

    (log "d")))

(dom/listen! (sel1 :#start) :click main)

