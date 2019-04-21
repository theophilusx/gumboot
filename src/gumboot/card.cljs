(ns gumboot.card)

(defn card [body & {:keys [header footer title card-class header-class footer-class]
                   :or {header nil
                        footer nil
                        title nil
                        card-class ""
                        header-class ""
                        footer-class ""}}]
  [:div {:class (str "card " card-class)}
   (if (not (nil? header))
     [:div {:class (str "card-header " header-class)}
      header])
   [:div.card-body
    (if (not (nil? title)) 
      [:h5.card-title title])
    body]
   (if (not (nil? footer)) 
     [:div {:class (str "card-footer " footer-class)}
      footer])])

