(ns gumboot.data-grid)

(defn grid-headings [headings hdr-attr col-attr]
  (into
   [:div hdr-attr]
   (for [h headings]
     [:div col-attr
      (str h)])))


(defn default-body-row [row row-attrs row-col-attrs]
  (into
   [:div row-attrs]
   (for [c (keys row)]
     [:div row-col-attrs (str (c row))])))

(defn data-grid
  [headings data-rows & {:keys [hdr-attrs hdr-col-attrs
                                body-attrs body-col-attrs]
                         :or {hdr-attrs {:class "row"}
                              hdr-col-attrs {:class "col"}
                              body-attrs {:class "row"}
                              body-col-attrs {:class "col"}}}]
  [:div
   [grid-headings headings hdr-attrs hdr-col-attrs]
   (for [r data-rows]
     [default-body-row r body-attrs body-col-attrs])])
