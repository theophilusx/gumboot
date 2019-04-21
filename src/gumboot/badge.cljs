(ns gumboot.badge)

(defn badge [txt class]
  [:span {:class (str "badge " class)} txt])

(defn badge-primary [txt class]
  [badge txt (str "badge-primary " class)])

(defn badge-secondary [txt class]
  [badge txt (str "badge-secondary " class)])

(defn badge-success [txt class]
  [badge txt (str "badge-success " class)])

(defn badge-warning [txt class]
  [badge txt (str "badge-warning " class)])

(defn badge-danger [txt class]
  [badge txt (str "badge-danger " class)])

(defn badge-info [txt class]
  [badge txt (str "badge-info " class)])

(defn badge-light [txt class]
  [badge txt (str "badge-light " class)])

(defn badge-dark [txt class]
  [badge txt (str "badge-dark " class)])

(defn badge-primary-p [txt class]
  [badge-primary txt (str "badge-pill " class)])

(defn badge-secondary-p [txt class]
  [badge-secondary txt (str "badge-pill " class)])

(defn badge-success-p [txt class]
  [badge-success txt (str "badge-pill " class)])

(defn badge-warning-p [txt class]
  [badge-warning txt (str "badge-pill " class)])

(defn badge-danger-p [txt class]
  [badge-danger txt (str "badge-pill " class)])

(defn badge-info-p [txt class]
  [badge-info txt (str "badge-pill " class)])

(defn badge-light-p [txt class]
  [badge-light txt (str "badge-pill " class)])

(defn badge-dark-p [txt class]
  [badge-dark txt (str "badge-pill " class)])

