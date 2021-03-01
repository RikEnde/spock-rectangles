package exercise.rectangles

import spock.lang.Specification

class RectanglesApplicationSpec extends Specification {

//    def "Failing test"() {
//        given:
//        throw new IllegalArgumentException()
//    }

    def "Parse string to point"() {
        expect:
        point == RectanglesApplication.parsePoint(line)

        where:
        point | line
        new Point(1, 2) | "1, 2"
        new Point(-1, 2) | "-1, 2"
        new Point(1, 2) | "1,2"
        new Point(-1, 2) | "-1,2"
        new Point(1, 2) | "1, 2    "
        new Point(-1, 2) | "   \t -1, 2"
        new Point(1, 2) | "1   ,2"
        new Point(-1, 2) | "-1   ,   2"
    }

    def "Invalid strings throw exceptipon"() {
        when:
        RectanglesApplication.parsePoint(line)

        then:
        def error = thrown(expectedException)
        error.message == message

        where:
        line    | expectedException        | message
        "1,2,3" | IllegalArgumentException | 'Not a point: 1,2,3'
        ""      | IllegalArgumentException | 'Not a point: '
        ",,"    | IllegalArgumentException | 'Not a point: ,,'
        "a,b"   | NumberFormatException    | 'For input string: "a"'
    }

    def "Invalid rectangle throws exception"() {
        when:
        def rect = new Rectangle(topLeft, bottomRight)

        then:
        def error = thrown(expectedException)

        where:
        topLeft | bottomRight   | expectedException
        new Point(0, 0) | new Point(5, 5) | IllegalArgumentException
        new Point(0, 0) | new Point(0, 0) | IllegalArgumentException
    }
}
