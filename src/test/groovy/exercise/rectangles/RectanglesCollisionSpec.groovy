package exercise.rectangles

import spock.lang.Specification
import spock.lang.Unroll

class RectanglesCollisionSpec extends Specification {

    @Unroll
    def "A intersects B: #testCase"() {
        expect:
        println "${r1} intersects ${r2}: ${intersects}"
        r1.intersects(r2) == intersects

        where:
        testCase | r1 | r2 | intersects
        "B on corner of A" | new Rectangle(new Point(0, 10), new Point(10, 0)) | new Rectangle(new Point(-1, 3), new Point(3, -1)) | true
        "A on corner of B" | new Rectangle(new Point(-1, 3), new Point(3, -1)) | new Rectangle(new Point(0, 10), new Point(10, 0)) | true

        "A left of B - NO" | new Rectangle(new Point(1, 5), new Point(3, 3)) | new Rectangle(new Point(4, 4), new Point(6, 2)) | false
        "B left of A - NO" | new Rectangle(new Point(4, 4), new Point(6, 2)) | new Rectangle(new Point(1, 5), new Point(3, 3)) | false

        "A above B - NO" | new Rectangle(new Point(1, 5), new Point(5, 1)) | new Rectangle(new Point(2, 8), new Point(6, 6)) | false
        "B above A - NO" | new Rectangle(new Point(2, 8), new Point(6, 6)) | new Rectangle(new Point(1, 5), new Point(5, 1)) | false

        "B overlaps one side of A" | new Rectangle(new Point(1, 6), new Point(6, 2)) | new Rectangle(new Point(5, 4), new Point(7, 3)) | true
        "A overlaps one side of B" | new Rectangle(new Point(5, 4), new Point(7, 3)) | new Rectangle(new Point(1, 6), new Point(6, 2)) | true

        "B left of A - NO" | new Rectangle(new Point(1, 4), new Point(3, 2)) | new Rectangle(new Point(5, 3), new Point(7, 1)) | false
        "A left of B - NO" | new Rectangle(new Point(5, 3), new Point(7, 1)) | new Rectangle(new Point(1, 4), new Point(3, 2)) | false

        "B inside on corner of A" | new Rectangle(new Point(1, 5), new Point(5, 1)) | new Rectangle(new Point(1, 2), new Point(2, 1)) | true
        "A inside on corner of B" | new Rectangle(new Point(1, 2), new Point(2, 1)) | new Rectangle(new Point(1, 5), new Point(5, 1)) | true

        "B outside on corner of A - NO" | new Rectangle(new Point(2, 5), new Point(5, 2)) | new Rectangle(new Point(5, 6), new Point(6, 5)) | false
        "A outside on corner of B - NO" | new Rectangle(new Point(5, 6), new Point(6, 5)) | new Rectangle(new Point(2, 5), new Point(5, 2)) | false

        "B inside A - NO" | new Rectangle(new Point(1, 6), new Point(6, 1)) | new Rectangle(new Point(2, 5), new Point(5, 2)) | false
        "A inside B - NO" | new Rectangle(new Point(2, 5), new Point(5, 2)) | new Rectangle(new Point(1, 6), new Point(6, 1)) | false

        "B adjacent right of A" | new Rectangle(new Point(1, 5), new Point(5, 1)) | new Rectangle(new Point(5, 3), new Point(7, 1)) | false
        "A adjacent right of B" | new Rectangle(new Point(5, 3), new Point(7, 1)) | new Rectangle(new Point(1, 5), new Point(5, 1)) | false
    }

    @Unroll
    def "A contains B: #testCase"() {
        expect:
        println "${r1} contains ${r2}: ${contains}"
        r1.contains(r2) == contains

        where:
        testCase | r1 | r2 | contains
        "A left of B - NO" | new Rectangle(new Point(1, 5), new Point(3, 3)) | new Rectangle(new Point(4, 4), new Point(6, 2)) | false
        "B left of A - NO" | new Rectangle(new Point(4, 4), new Point(6, 2)) | new Rectangle(new Point(1, 5), new Point(3, 3)) | false

        "A above B - NO" | new Rectangle(new Point(1, 5), new Point(5, 1)) | new Rectangle(new Point(2, 4), new Point(6, 6)) | false
        "B above A - NO" | new Rectangle(new Point(2, 4), new Point(6, 6)) | new Rectangle(new Point(1, 5), new Point(5, 1)) | false

        "B inside A" | new Rectangle(new Point(1, 6), new Point(6, 1)) | new Rectangle(new Point(2, 5), new Point(5, 2)) | true
        "A inside B - NO" | new Rectangle(new Point(2, 5), new Point(5, 2)) | new Rectangle(new Point(1, 6), new Point(6, 1)) | false

        "B equals A" | new Rectangle(new Point(1, 6), new Point(6, 1)) | new Rectangle(new Point(1, 6), new Point(6, 1)) | true

        "B overlaps one side of A - NO" | new Rectangle(new Point(1, 6), new Point(6, 2)) | new Rectangle(new Point(5, 4), new Point(7, 3)) | false
        "A overlaps one side of B - NO" | new Rectangle(new Point(5, 4), new Point(7, 3)) | new Rectangle(new Point(1, 6), new Point(6, 2)) | false

        "B inside on corner of A" | new Rectangle(new Point(1, 5), new Point(5, 1)) | new Rectangle(new Point(1, 2), new Point(2, 1)) | true
        "A inside on corner of B - NO" | new Rectangle(new Point(1, 2), new Point(2, 1)) | new Rectangle(new Point(1, 5), new Point(5, 1)) | false
    }

    @Unroll
    def "A adjacent to B: #testCase"() {
        expect:
        println "${r1} adjacent to ${r2}: ${adjacent}"
        r1.adjacent(r2) == adjacent

        where:
        testCase | r1 | r2 | adjacent
        "A left of B - NO" | new Rectangle(new Point(1, 5), new Point(3, 3)) | new Rectangle(new Point(4, 4), new Point(6, 2)) | false
        "B left of A - NO" | new Rectangle(new Point(4, 4), new Point(6, 2)) | new Rectangle(new Point(1, 5), new Point(3, 3)) | false

        "A above B - NO" | new Rectangle(new Point(1, 5), new Point(5, 1)) | new Rectangle(new Point(2, 4), new Point(6, 6)) | false
        "B above A - NO" | new Rectangle(new Point(2, 4), new Point(6, 6)) | new Rectangle(new Point(1, 5), new Point(5, 1)) | false

        "B inside A - NO" | new Rectangle(new Point(1, 6), new Point(6, 1)) | new Rectangle(new Point(2, 5), new Point(5, 2)) | false
        "A inside B - NO" | new Rectangle(new Point(2, 5), new Point(5, 2)) | new Rectangle(new Point(1, 6), new Point(6, 1)) | false

        "B overlaps one side of A - NO" | new Rectangle(new Point(1, 6), new Point(6, 2)) | new Rectangle(new Point(5, 4), new Point(7, 3)) | false
        "A overlaps one side of B - NO" | new Rectangle(new Point(5, 4), new Point(7, 3)) | new Rectangle(new Point(1, 6), new Point(6, 2)) | false

        "B inside on corner of A - NO" | new Rectangle(new Point(1, 5), new Point(5, 1)) | new Rectangle(new Point(1, 2), new Point(2, 1)) | false
        "A inside on corner of B - NO" | new Rectangle(new Point(1, 2), new Point(2, 1)) | new Rectangle(new Point(1, 5), new Point(5, 1)) | false

        "B stuck right to A (sub-line)" | new Rectangle(new Point(0, 5), new Point(5, 0)) | new Rectangle(new Point(5, 4), new Point(7, 3)) | true
        "A stuck right to B (sub-line)" | new Rectangle(new Point(5, 4), new Point(7, 3)) | new Rectangle(new Point(0, 5), new Point(5, 0)) | true

        "B stuck right to A (proper)" | new Rectangle(new Point(0, 5), new Point(5, 0)) | new Rectangle(new Point(5, 5), new Point(7, 0)) | true
        "A stuck right to B (proper)" | new Rectangle(new Point(5, 5), new Point(7, 0)) | new Rectangle(new Point(0, 5), new Point(5, 0)) | true

        "B stuck right to A (partial)" | new Rectangle(new Point(0, 5), new Point(5, 0)) | new Rectangle(new Point(5, 6), new Point(7, 1)) | true
        "A stuck right to B (partial)" | new Rectangle(new Point(5, 6), new Point(7, 1)) | new Rectangle(new Point(0, 5), new Point(5, 0)) | true

        "B on top of A (sub-line)" | new Rectangle(new Point(0, 5), new Point(5, 0)) | new Rectangle(new Point(3, 7), new Point(4, 5)) | true
        "A on top of B (sub-line)" | new Rectangle(new Point(3, 7), new Point(4, 5)) | new Rectangle(new Point(0, 5), new Point(5, 0)) | true

        "B on top of A (proper)" | new Rectangle(new Point(0, 5), new Point(5, 0)) | new Rectangle(new Point(0, 7), new Point(5, 5)) | true
        "A on top of B (proper)" | new Rectangle(new Point(0, 7), new Point(5, 5))  | new Rectangle(new Point(0, 5), new Point(5, 0)) | true

        "B on top of A (partial)" | new Rectangle(new Point(0, 5), new Point(5, 0)) | new Rectangle(new Point(-1, 6), new Point(4, 5)) | true
        "A on top of B (partial)" | new Rectangle(new Point(-1, 6), new Point(4, 5)) | new Rectangle(new Point(0, 5), new Point(5, 0)) | true
    }

//    def "Failing test"() {
//        expect: false
//    }
}
