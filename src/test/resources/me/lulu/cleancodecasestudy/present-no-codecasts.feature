Feature: Present Codecasts
  # Enter feature description here

  Scenario: Present No Codecasts
    Given codecasts:
      | title | published |
      | A     | 3/1/2014  |
      | B     | 3/2/2014  |
      | C     | 2/18/2014 |

    Given no codecasts
    Given user "U"
    And user "U" logged in
    Then there will be no codecasts presented for "U"

  Scenario: Present Viewable Codecasts Sorted By Published Date
    Given codecasts:
      | title | published |
      | A     | 6/30/2023 |
      | B     | 7/1/2023  |
      | C     | 6/29/2023 |

    Given user "U"
    And user "U" logged in
    And license for "U" to view "A"
    Then there will be codecasts presented for "U":
      | title | publication date | picture | description | viewable | downloadable |
      | C     | 2023-06-29       | C       | C           | -        | -            |
      | A     | 2023-06-30       | A       | A           | +        | -            |
      | B     | 2023-07-01       | B       | B           | -        | -            |
