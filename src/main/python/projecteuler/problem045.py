#!/usr/bin/python
"""
Triangular, pentagonal, and hexagonal
Problem 45
Triangle, pentagonal, and hexagonal numbers are generated by the following formulae:

Triangle        Tn=n(n+1)/2     1, 3, 6, 10, 15, ...
Pentagonal      Pn=n(3n-1)/2     1, 5, 12, 22, 35, ...
Hexagonal       Hn=n(2n-1)       1, 6, 15, 28, 45, ...
It can be verified that T285 = P165 = H143 = 40755.

Find the next triangle number that is also pentagonal and hexagonal.
"""

def trianglenumber(n):
    return .5 * n * (n + 1)

def trianglenumbers(n):
    q = []
    for i in range(1, n + 1):
        q.append(trianglenumber(i))
    return q

def pentagonalnumber(n):
    return n * (3 * n - 1) * .5

def pentagonalnumbers(n):
    q = []
    for i in range(1, n + 1):
        q.append(pentagonalnumber(i))
    return q

def hexagonalnumber(n):
    return n * (2 * n - 1)

def hexagonalnumbers(n):
    q = []
    for i in range(1, n + 1):
        q.append(hexagonalnumber(i))
    return q

def main():
    hold = 0
    t = trianglenumbers(100000)
    h = hexagonalnumbers(100000)
    p = pentagonalnumbers(100000)
    for i in range(len(t)):
        if t[i] in h and t[i] in p:
            hold = i
    print(t[hold])

if __name__ == "__main__":
    main()
