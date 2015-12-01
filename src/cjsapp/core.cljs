(ns cjsapp.core
  (:require [reagent.core :as reagent :refer [atom]]))

(enable-console-print!)

(println "Edits to this text should show up in your developer console.")

;; define your app data so that it doesn't get over-written on reload

(defonce app-state (atom {:text "Hello world!"
                          :selected nil
                          :items ["apple" "banana" "kiwi"]}))

(def messages ["Hello" "Bye" "WTF?!"])

(defn items []
  [:ul
   (doall (for [item (:items @app-state)]
            [:li {:key item
                  :class (if (= (:selected @app-state) item) "selected" "not-selected")
                  :onClick #(swap! app-state assoc :selected item)}
             (str item)]))])
  ;; (into
  ;;  [:ul]
  ;;  (for [item (:items @app-state)]
  ;;    [:li {:key item
  ;;          :class (if (= (:selected @app-state) item) "selected" "not-selected")
  ;;          :onClick #(swap! app-state assoc :selected item)}
  ;;     (str item)])))

(defn hello-world []
  [:div
    [:h1 (:text @app-state)]
    [:button {:onClick (fn []
                         (swap! app-state assoc :text (nth messages (rand-int (count messages)))))}
     "click me!"]
    [items]])

(reagent/render-component [hello-world]
                          (. js/document (getElementById "app")))


(defn on-js-reload []
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  ;; (swap! app-state update-in [:__figwheel_counter] inc)
)
