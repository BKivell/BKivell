#include <iostream>
#include <vector> 
#include <fstream>
#include <iomanip>  
using namespace std;

//static float failed = 0;
//static float complete = 0;

class Matrix
{
public: // Set access modifier to public

	//---------------------------------------------------[VARIABLES]---------------------------------------------------
	vector<double> elements;   // Array containing data 
	vector<double*> rows;      // Array of pointers to start of each row 
	unsigned cols; // Number of columns 
	int size = 13; // Size of matrix (size x size)


	//---------------------------------------------------[CONSTRUCTORS]---------------------------------------------------
	Matrix() // Default constructor to let array intialize without input
	{
		int nrows = size; int ncols = size;
		elements.resize(nrows * ncols);
		rows.resize(nrows);
		cols = ncols;

		// Loops through the elements by the number of columns to set the pointer of the row to the first element
		int count = 0;
		for (int i = 0; i < nrows * ncols; i += ncols)
		{
			rows[count] = (&elements[i]);
			count++;
		}
	}
	Matrix(int colRowCount) // Constructor to allow custom size in case wanted
	{
		// SETUP ROWS AND COLUMNS
		int nrows = colRowCount; int ncols = colRowCount;
		elements.resize(nrows * ncols);
		rows.resize(nrows);
		cols = ncols;

		// Loops through the elements by the number of columns to set the pointer of the row to the first element
		int count = 0;
		for (int i = 0; i < nrows * ncols; i += ncols)
		{
			rows[count] = (&elements[i]);
			count++;
		}
	}


	//---------------------------------------------------[OPERATIONS]---------------------------------------------------
	// Method returns the element in the ith row and jth column 
	double get(unsigned irow, unsigned jcol)
	{
		double element;
		// Sets pointer to the correct row
		double* pointer = rows[irow];
		// Moves the pointer by the column number
		pointer += jcol;
		// Returns the element which the pointer is at
		element = *pointer;

		return element;
	}

	// Method sets the element in the ith row and jth column 
	void set(unsigned irow, unsigned jcol, double value)
	{
		// Sets pointer to correct row
		double* pointer = rows[irow];
		// Moves pointer by column number
		pointer += jcol;
		// Sets the value at the pointer to be the input value
		*pointer = value;
	}

	// Swap the ith and jth rows 
	void interchange(unsigned irow, unsigned jrow)
	{
		// Pointers i, j point to the respective row beggining
		double* i = rows[irow];
		double* j = rows[jrow];
		// Swaps the pointer addresses around
		rows[jrow] = i;
		rows[irow] = j;
	}

	// Multiply the ith row by x 
	void multiply(unsigned irow, double x)
	{
		double temp;
		// Loops through elements in a row
		for (int i = 0; i <= cols - 1; i++)
		{
			// Gets the element to be multiplied
			temp = get(irow, i);
			// Multiplys element by x
			set(irow, i, temp * x);
		}
	}

	// add x times the jth row to the ith row 
	void add(unsigned irow, double x, unsigned jrow)
	{
		double a, b;
		// Loops through elements in a row
		for (int i = 0; i < cols; i++)
		{
			// a is x times the element in jth row
			a = get(jrow, i) * x;
			// b is the element in the ith row
			b = get(irow, i);
			// Sets the element in ith row to be the addition
			set(irow, i, a + b);
		}
	}

	// Displays the matrix to screen
	void display()
	{
		for (unsigned irow = 0; irow < size; irow++)
		{
			for (unsigned icol = 0; icol < size; icol++)
			{
				cout << setw(3) << setprecision(3) << get(irow, icol) << "\t";
			}
			cout << endl;
		}
		cout << endl;
	}

	// Divides ith row by x
	void divide(int irow, double x)
	{
		for (int j = 0; j < size; j++)
		{
			rows[irow][j] = rows[irow][j] / x;
		}
	}

	// Fills a matrix with random numbers
	void fill()
	{
		for (int i = 0; i < size; i++)
		{
			for (int j = 0; j < size; j++) {

				double random = (double)rand() / RAND_MAX;
				random = random * (100);
				rows[i][j] = round(random);
			}
		}
	}

	// Returns the product as a matrix
	Matrix product(Matrix m1, Matrix m2)
	{
		Matrix prodMatrix(size);

		for (int i = 0; i < size; i++)
		{
			for (int j = 0; j < size; j++)
			{
				double value = 0;
				for (int k = 0; k < size; k++)
				{
					value += m1.get(i, k) * m2.get(k, j);
					prodMatrix.set(i, j, value);
				}
			}
		}
		return prodMatrix;
	}

	// Compares input matrix to identity matrix to see if they are equal within the threshold
	bool compare(Matrix matrix, double threshold)
	{
		bool status = true;
		for (int i = 0; i < size; i++) // Loop ith row
		{
			if (matrix.get(i, i) > (1 + threshold) || matrix.get(i, i) < (1 - threshold)) // 1 Expected on diagonal
			{
				status = false;
			}
		}
		return status;
	}

	// Returns inverse of matrix
	Matrix invert()
	{
		Matrix original(size); // Copy of input matrix
		Matrix identity(size); // Identity matrix (Will end up as inverse matrix after operations)
		Matrix inverse(size); // Inverse of input matrix (Will end up as identity matrix after operations)

		// Copy values from input matrix to original inverse
		for (int i = 0; i < size; i++)
		{
			for (int j = 0; j < size; j++)
			{
				original.set(i, j, (get(i, j)));
				inverse.set(i, j, (get(i, j)));
			}
		}

		// Set values for identity matrix (1's on diagonal)
		for (int i = 0; i < size; i++)
		{
			for (int j = 0; j < size; j++)
			{
				identity.set(i, j, 0.0);
				if (i == j)
				{
					identity.set(i, j, 1);
				}
			}
		}

		// Loop through matrix
		for (int j = 0; j < size; j++)
		{
			double pivotValue = inverse.get(j, j); // Stores value of the element at (x, x)

			// Loop through row
			for (int i = 0; i < size; i++) {
				if (i == j)
				{
					continue;
				}
				else
				{
					double scale = -inverse.get(i, j) / pivotValue; // Gets the scale value
					inverse.add(i, scale, j); // Adds scale times the jth row to the ith row
					identity.add(i, scale, j); // Perform same operation to identity to act as augmented matrix
				}
			}
		}

		// Loop through and divide row by pivot value to get pivot to = 1 (all values on diagonal)
		for (int j = 0; j < size; j++) {
			identity.divide(j, inverse.get(j, j)); // Divides the jth row by the pivot value 
			inverse.divide(j, inverse.get(j, j)); // Performs same operation
		}


		// Check if the matrix is within the threshold
//		if (compare(product(original, identity), 0.00000000001)) {
//			complete++;
//		}
//		else {
//			failed++;
//		}

		// Display number of found and failed inverse matrix
//		float rate = failed / (complete + failed) * 100;
//		cout << setprecision(5) << "Found: " << complete << "   Failed: " << failed << "   (" << rate << "% Fail Rate)" << endl;

		return identity;
	}
};
