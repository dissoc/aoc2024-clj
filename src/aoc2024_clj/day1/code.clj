(ns aoc2024-clj.day1.code
  (:require [clojure.string :as s]))

(def input-data (slurp "resources/day1/data"))

(defn part-1 []
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

(defn part-2
  "NOTE: could optimize to not recount on repeat left side"
  []
  (let [{left  :left
         right :right}
        (loop [input  (-> input-data s/split-lines)
               output {:left [] :right {}}]
          (let [[l-s r-s] (s/split (first input) #"   ")

                output (-> output
                           (update :left conj (Integer. l-s))
                           (update-in [:right (Integer. r-s)] (fn [n]
                                                                (if (nil? n)
                                                                  1
                                                                  (inc n)))))]
            (if (next input)
              (recur (rest input) output)
              output)))]
    (loop [left left
           sum  0]
      (let [left-num   (first left)
            left-count (get right left-num)
            sum        (+ sum (* left-num
                                 (if left-count
                                   left-count
                                   0)))]
        (if (next left)
          (recur (rest left) sum)
          sum)))))
