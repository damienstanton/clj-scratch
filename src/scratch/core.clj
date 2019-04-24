(ns scratch.core
  (:require [clojure.string :as str]))

(def input "Damien((Kristian)Stanton)")

(def raw-reversed-input (reverse input))

(def triple #(* % 3))

(defn nudge
  "Increment by some value"
  [n]
  #(+ % n))

(def nudge-by-5 (nudge 5))

(nudge-by-5 5)


;; Example
(def asym-hobbit-body-parts [{:name "head" :size 3}
                             {:name "left-eye" :size 1}
                             {:name "left-ear" :size 1}
                             {:name "mouth" :size 1}
                             {:name "nose" :size 1}
                             {:name "neck" :size 2}
                             {:name "left-shoulder" :size 3}
                             {:name "left-upper-arm" :size 3}
                             {:name "chest" :size 10}
                             {:name "back" :size 10}
                             {:name "left-forearm" :size 3}
                             {:name "abdomen" :size 6}
                             {:name "left-kidney" :size 1}
                             {:name "left-hand" :size 2}
                             {:name "left-knee" :size 2}
                             {:name "left-thigh" :size 4}
                             {:name "left-lower-leg" :size 3}
                             {:name "left-achilles" :size 1}
                             {:name "left-foot" :size 2}])

(defn matching-part
  [part]
  {:name (str/replace (:name part) #"^left-" "right-")
   :size (:size part)})

(defn symmetrize-body-parts
  "Expects a seq of maps that have a :name and :size"
  [asym-body-parts]
  (loop [remaining-asym-parts asym-body-parts
         final-body-parts []]
    (if (empty? remaining-asym-parts)
      final-body-parts
      (let [[part & remaining] remaining-asym-parts]
        (recur remaining
               (into final-body-parts
                     (set [part (matching-part part)])))))))

(symmetrize-body-parts asym-hobbit-body-parts)

(def dalmatian-list
  ["Pongo" "Perdita" "Puppy 1" "Puppy 2"])

(let [[pongo & dalmatians] dalmatian-list]
  [pongo dalmatians])

;; Exercises
;; 1.
(defn use-string
  [name]
  (str "Hello, " name "."))

(defn make
  [dfn & elements]
  (let [f (dfn :fn)] f elements))

(defn make-list
  [& elements]
  (list elements))

(defn make-map
  [& elements]
  (map elements))

;; TODO for 1. list and hashmap examples

;; 2.
(defn add-100
  [n]
  (+ 100 n))

;; 3.
(defn dec-maker
  [n]
  #(- % n))


;; Parse nested parens (codesignal)
;; ----------

(defn reverse-in-parens
  [input]
  (let [pat (re-find #"\([^()]*\)" input)]
    (if (nil? pat)
      input
      (reverse-in-parens (clojure.string/replace input pat (clojure.string/reverse (subs pat 1 (dec (count pat)))))))))

(defn reverseInParentheses [inputString]
  (let [pattern (re-find #"\([^()]*\)" inputString)]
    (println (str pattern))
    (if (nil? pattern)
      inputString
      (reverseInParentheses (clojure.string/replace inputString pattern (clojure.string/reverse (subs pattern 1 (dec (count pattern)))))))))

(def damien "Damien((K)Stanton)")



;; Good one
;; (re-seq #"\w+" (str (re-seq #"\([^)]+\)" string)))
;; #(re-seq #"\w+" (str (re-seq #"\([^)]+\)" %)))

; (defn replacer
;   [string]
;   (let [pattern (re-pattern #"\([^)]+\)")
;         ]))

; (defn all-in-one
;   [string]
;   (let [stream (seq string)
;         rev-match ()]))

; (clojure.string/join "" (map #(second %) (re-seq (re-pattern (str "\\(" "([\\s\\S]*?)" "\\)")) damien)))

; (defn kill-parens
;   [string]
;   (for [c (re-seq #"\(.*?\)" string)]

;     (clojure.string/replace c #"\(" "")))