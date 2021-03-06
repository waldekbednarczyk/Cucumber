Feature: SOFLAB TA Framework training


  Scenario: Social media icons displayed
    Given user is on Home Page
    When scroll to the footer
    Then Facebook icon is displayed
    And Twitter icon is displayed
    And Google icon is displayed
    And Youtube icon is displayed
 

	Scenario: Google search and subcategories availability
		Given user is on Google search page
		When accept cookies
		And search for "automationpractice"
		Then automationpractice website found
		When enter the automationpractice website
		And enter the Women category
		Then TOPS subcategory is available
		And DRESSES subcategory is available


	Scenario: Item price and total price correct
		Given user is on Home Page
		When add to cart Blouse item
		Then the item price prive should be "$27.00"
		And the total price prive should be "$29.00"


	Scenario Outline: Verify + and - buttons
    Given user is on Home Page
    When add to cart Blouse item
    And proceed to checkout
    And click the plus button <plus> times
    And lick the minus button <minus> times
    Then the total price is <price>
    
    Examples:
    | plus | minus | price |
    | 1    | 1     |"$27.00"|
    | 2    | 1     |"$54.00" |
    
    Scenario: Send a message via Contact us
    	Given user is on Home Page
    	When enter Contact us Page
    	And select "Customer service" from Subject Heading
    	And enter "test@op.pl" Email address
    	And enter "test" Order reference
    	And Attach a file
    	And enter "test" message
    	And click Send
    	Then the confirmation message is displayed
    
    Scenario: Bestsellers names and prices
	    Given user is on Home Page
	    When open bestsellers section
	    And log names and prices to console
	    And log names and prices to file
	    Then capture a screenshot of displayed products
    
    Scenario: Create an account and log in
	    Given user is on Home Page
	    When enter Sign in
	    And enter email to create an account
	    And click Create an account
	    And enter informations to create a account
	    And click register
	    Then the sign in confirmation message is displayed
	    When click logout
	    And sign in with new accout details
	    Then the sign in confirmation message is displayed
    
    #Scenario: Capture all different ID
   	# Given user is on Home Page
   	
    
    Scenario: Capture all different ID
	    Given user is on Home Page
	    When capture all diffrent IDs
	    Then save the IDs to a file
    
    Scenario: Capture all different ID
	    Given user is on Home Page
	    When enter the Blouse Quick viev
	    And download thumbs images
	    Then log the number of download images
 
	