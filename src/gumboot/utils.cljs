(ns gumboot.utils
  (:require [clojure.string :as s]))

(defn defkey [s]
  (str (s/replace s #"\s" "_") "_key"))
