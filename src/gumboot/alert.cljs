(ns gumboot.alert)

(defn alert [txt class]
  [:div {:class (str "alert " class) :role "alert"} txt])

(defn alert-primary [txt class]
  [alert txt (str "alert-primary " class)])

(defn alert-secondary [txt class]
  [alert txt (str "alert-secondary " class)])

(defn alert-success [txt class]
  [alert txt (str "alert-success " class)])

(defn alert-danger [txt class]
  [alert txt (str "alert-danger " class)])

(defn alert-warning [txt class]
  [alert txt (str "alert-warning " class)])

(defn alert-info [txt class]
  [alert txt (str "alert-info " class)])

(defn alert-light [txt class]
  [alert txt (str "alert-light " class)])

(defn alert-dark [txt class]
  [alert txt (str "alert-dark " class)])

(defn alert-dismissible [txt class]
  [:div {:class (str "alert alert-dismissible fade show " class) :role "alert"}
   txt
   [:button.close {:type "button"
                   :data-dismiss "alert"}
    [:span "X"]]])

(defn alert-primary-d [txt class]
  [alert-dismissible txt (str "alert-primary " class)])

(defn alert-secondary-d [txt class]
  [alert-dismissible txt (str "alert-secondary " class)])

(defn alert-success-d [txt class]
  [alert-dismissible txt (str "alert-success " class)])

(defn alert-danger-d [txt class]
  [alert-dismissible txt (str "alert-danger " class)])

(defn alert-warning-d [txt class]
  [alert-dismissible txt (str "alert-warning " class)])

(defn alert-info-d [txt class]
  [alert-dismissible txt (str "alert-info " class)])

(defn alert-light-d [txt class]
  [alert-dismissible txt (str "alert-light " class)])

(defn alert-dark-d [txt class]
  [alert-dismissible txt (str "alert-dark " class)])

