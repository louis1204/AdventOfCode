package main

import (
	"bufio"
	"fmt"
	"log"
	"math"
	"os"
	"strconv"
	"strings"
)

func DoDay3() {
	file, err := os.Open("input3.txt")
	if err != nil {
		log.Fatal("Failed to open file")
	}
	scanner := bufio.NewScanner(file)
	scanner.Split(bufio.ScanLines)

	var totalNum int64 = 0
	var countOnes []int64 = make([]int64, 0)
	for scanner.Scan() {
		parts := strings.Split(scanner.Text(), "")
		for i := 0; i < len(parts); i++ {
			bit, err := strconv.ParseInt(parts[i], 10, 0)
			if err != nil {
				log.Fatal("Error parsing string")
			}
			if i == len(countOnes) {
				countOnes = append(countOnes, bit)
			} else {
				countOnes[i] += bit
			}
		}
		totalNum++
	}
	var gamma int64 = 0
	var epsilon int64 = 0
	totalNum /= 2
	for i := len(countOnes) - 1; i >= 0; i-- {
		if countOnes[i] > totalNum {
			gamma += int64(math.Pow(2, float64(len(countOnes)-1-i)))
		} else {
			epsilon += int64(math.Pow(2, float64(len(countOnes)-1-i)))
		}
	}
	res := gamma * epsilon
	// fmt.Println(countOnes)
	// fmt.Println(totalNum)
	// fmt.Println(gamma)
	// fmt.Println(epsilon)
	fmt.Printf("day 3 res part 1: %d\n", res)
}
