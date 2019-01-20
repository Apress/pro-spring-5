package com.apress.prospring5.ch14


Singer singer = new Singer(firstName: 'John', lastName: 'Mayer', birthDate: new Date(
		(new GregorianCalendar(1977, 9, 16)).getTime().getTime()))

Singer anotherSinger =
		new Singer(firstName: 39, lastName: 'Mayer', birthDate: new Date(
				(new GregorianCalendar(1977, 9, 16)).getTime().getTime()))

println singer
println anotherSinger

println singer.firstName + 39
println anotherSinger.firstName + 39

//Simplified Syntax
def list = ['This', 'is', 'John Mayer']
println list

assert list.size() == 3
assert list.class == ArrayList

assert list.reverse() == ['John Mayer', 'is', 'This']

assert list.sort{ it.size() } == ['is', 'This', 'John Mayer']

assert list[0..1] == ['is', 'This']

def names = ['John', 'Clayton', 'Mayer']

names.each {println 'Hello: ' + it}

//Closure
def map = ['a': 10, 'b': 50]

Closure square = {key, value -> map[key] = value * value}

map.each square

println map
