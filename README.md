# spock-rectangles
Command line app with data driven spock tests

# usage

```bash
./run.sh

(...)

Top left coords A: 0,10
Bottom right coords A: 10,0
Top left coords B: -1,3
Bottom right coords B: 3,-1
Rectangle{topLeft=Point{x=0, y=10}, bottomRight=Point{x=10, y=0}}
Rectangle{topLeft=Point{x=-1, y=3}, bottomRight=Point{x=3, y=-1}}
A intersects B: true
A contains B: false
A adjacent to B: false

```

# Known issues:

I had to time box this. Known bugs and missing features:
- The points of intersection are not reported.