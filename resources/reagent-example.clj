(fn hello []
  (r/with-let [value (r/atom "World")]
    [:div
     [:p (str "Hello, " @value "!")]
     [:input {:value     @value
              :on-change #(reset! value
                            (oget % ["target" "value"]))}]]))
