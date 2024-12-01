(ns aoc2024-clj.day1.code
  (:require [clojure.string :as s]))

(def input-data (slurp "resources/day1/data"))

(defn day1 []
  (let [{left  :left
         right :right}
        (loop [input  (-> input-data s/split-lines)
               output {:left [] :right []}]
          (let [[l-s r-s] (s/split (first input) #"   ")
                output    (-> output
                              (update :left conj (Integer. l-s))
                              (update :right conj (Integer. r-s)))]
            (if (next input)
              (recur (rest input) output)
              (-> output
                  (update :left sort)
                  (update :right sort)))))]
    (loop [left  left
           right right
           sum   0]
      (let [sum (+ sum (abs (- (first left) (first right))))]
        (if (next left)
          (recur (rest left) (rest right) sum)
          sum)))))
