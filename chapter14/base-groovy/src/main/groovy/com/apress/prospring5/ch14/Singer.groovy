package com.apress.prospring5.ch14

/**
 * Created by iuliana.cosmina on 6/25/17.
 */
class Singer {
	def firstName
	def lastName
	def birthDate

	String toString() {
		"($firstName,$lastName,$birthDate)"
	}
}


