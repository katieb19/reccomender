

Description of your code:

We started by working on the ListObjsData, which serves to store our table through a list of rows and their respective
attributes.

Then we continued by working on creating both the Node and Edge classes. The node class has an attribute and a list of
the edges it will be connected to in order to create the tree. Our edge class also has an attribute, and also stores a
value and a decision, which could be a final decision or a continuation for another tree.

We proceeded to create a class for a final decision that represents the leave once a decision has been made.

The next part was the implementation of the tree generator class that stores the data and also keeps track of the
root/first node of the tree.


How your classes work together:

In order to make the dataset that we planned to use to generate our tree, we implemented a ListObjsData class. This
class implements the IAttributeDataset<T> interface and takes in a class that implements an IAttributeDatum interface.
Finally, we created a Vegetable class that implements IAttributeDatum which, in addition to the Candidate class,
could be used in the ListObjsData class.
In order to make the tree generator we made a TreeGenerator class which implemented the IGenerator class and took
in an IAttributeDatum class. Within the TreeGenerator class, we took in the data we made from the ListObjsData class
as well as created a root field. This root field would be populated with our tree. Our tree was represented by the
INode interface. The INode interface was implemented by a FinalDecision class and a Node class. The Node class had
fields representing the attribute of the Node and a list of Edge class objects. The Edge class represented our subtrees.
Finally, our FinalDecision class was used to represent the end of the tree and had a field that represented an object
with the result of the tree or subtree.


Your group members:

Ruth Giorgis, Catherine Baumgarten, Diana Manzano, Valerie Aguilar Dellisanti

Your subgroup members:
Catherine Baumgarten
Valerie Aguilar Dellisanti



SRC

QUESTION 1

We chose to talk about the “Shaping the candidate pool” step. During this part of the process, the algorithm decides
what kind of people the advertising should be targeted to. For us, this is very problematic because minorities in
certain industries / fields are less likely to even click on an advertisement that shows a certain job if they don’t
feel qualified enough, regardless of the actual qualifications they might have. It is very well known for example that
women don’t apply to jobs unless they are 100% sure they do meet the requirements (Article:
https://hbr.org/2014/08/why-women-dont-apply-for-jobs-unless-theyre-100-qualified).
Having an algorithm that predicts who is going to actually click on the advertisement seems then very exclusionary,
especially for underrepresented groups in that specific field. For example, the algorithm would show lower paying
positions to women and people of color because that is how it has historically been and there is still a big percentage
of those jobs filled with the traditional stereotypical workers. However, in order to change and actually close the gap,
those biases need to be eliminated, but they won’t if the algorithm keeps taking into account “who would click on
the advertisement” rather than the actual qualifications of applicants.



QUESTION 2

Report Ratios

Cis Female Ratio hired: 	0.00
Cis Male Ratio hired: 		0.07

Cis Female Ratio hired: 	0.06
Cis Male Ratio hired: 		0.06

Cis Female Ratio hired: 	0.0
Cis Male Ratio hired: 		0.0

Cis Female Ratio hired: 	0.01
Cis Male Ratio hired: 		0.02

Cis Female Ratio hired: 	0.29
Cis Male Ratio hired: 		0.33

Cis Female Ratio hired: 	0.0
Cis Male Ratio hired: 		0.0

Cis Female Ratio hired: 	0.0
Cis Male Ratio hired: 		0.0

Cis Female Ratio hired: 	0.26
Cis Male Ratio hired: 		0.28

Cis Female Ratio hired: 	0.01
Cis Male Ratio hired: 		0.03

Cis Female Ratio hired: 	0.03
Cis Male Ratio hired: 		0.04



First Five Ratios

Cis Female Ratio hired: 	0.00
Cis Male Ratio hired: 		0.07

Cis Female Ratio hired: 	0.06
Cis Male Ratio hired: 		0.06

Cis Female Ratio hired: 	0.01
Cis Male Ratio hired: 		0.02

Cis Female Ratio hired: 	0.29
Cis Male Ratio hired: 		0.33

Cis Female Ratio hired: 	0.26
Cis Male Ratio hired: 		0.28



Given the fact that all candidates have equal qualifications and the only attribute that varies is the gender, we can
conclude from the data that there is a bias that favors hiring men over women for positions. Rarely, the ratios are
equal or 0. However, most of the time the ratio of male hired workers is higher than the ratio of females hired.
Overall, this difference in hired workers reveals the gender bias in the algorithm.


QUESTION 3

Equal - data

Cis Female Ratio hired: 	0.06
Cis Male Ratio hired: 		0.11

Cis Female Ratio hired: 	0.07
Cis Male Ratio hired: 		0.11

Cis Female Ratio hired: 	0.02
Cis Male Ratio hired: 		0.02

Cis Female Ratio hired: 	0.08
Cis Male Ratio hired: 		0.13

Cis Female Ratio hired: 	0.07
Cis Male Ratio hired: 		0.13

We still see the bias in the equal data file. The equal data spreadsheet contains the candidates ordered by gender
unlike the unequal one which has them randomly ordered. These changes don’t make a difference for the algorithm since
the tree generator chooses attributes randomly. Despite women being listed first, they still are not being favored by
the algorithm.


QUESTION 4
In our code, we randomly choose an attribute to partition the list. As a result, this can drastically impact bias.
This is because the order attributes are evaluated can impact the produced decision for the attribute being predicted.
Since the order attributes are evaluated can produce a variety of decisions, the amount of resulting bias will vary
based on how the attributes are evaluated.


QUESTION 5

The classifier produces different hiring rates each time we run it because it chooses a different attribute every time
it is being run. If we chose the same attribute to split on each time, it still wouldn’t eliminate bias as it wouldn’t
be guaranteed that maintaining the order attributes are being evaluated would produce less bias. In order to produce
less bias, understanding how the algorithm can potentially produce bias can best help alter the algorithm. A solution
to this would be taking into account different attributes at different times while running the algorithm.


QUESTION 6

Yes, we still see a hiring bias. It is because leadership and last position duration are not really gender-neutral
attributes. There is still a gender-gap in C-level and other leadership/management positions. An example of this is
how women account only for 5% of employees in venture capital and out of that 5%, only 11% make it to a senior-level
position. If this was an algorithm for a venture capital company, only 0.55% of females would have leadership experience
in that field (See Article:
https://www.inc.com/lisa-abeyta/
women-now-make-up-almost-five-percent-of-
investors-in-us.html#:~:text=It%20is%20still%20embarrassingly%20low,report%20from%20Women%20in%20VC.)




Big Picture:

BiasTest has many limitations when it comes to considering candidates’ backgrounds. It doesn’t take into account
racial/ethnic backgrounds, low-income/first-generation status, disabilities, traumas and/or hardships, etc. The
strength of BiasTest is that it automates the hiring process, making it less costly to the company since there is no
need for someone to go over the candidates. Additionally, as the article states, the algorithm can’t criminalize nor
control underrepresented minorities because the data is not even part of the set. However, this is extremely unfair to
applicants because systemic inequalities are not considered. Similar to college admission processes, the application
process needs to be holistic and take all those experiences and backgrounds into consideration, since it is unfair to
compare two candidates with different privileges, access to opportunities, and challenges. As Baraba’s states, the
greater question to be analyzed is who benefits from data and if including traditionally underrepresented candidates
would actually end up benefiting those communities or only worsening the gap by enriching the ones with power over those
technologies.



