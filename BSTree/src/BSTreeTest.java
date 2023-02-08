import static org.junit.Assert.*;

import java.util.Comparator;
import java.util.NoSuchElementException;
import org.junit.Test;

public class BSTreeTest
{
	/**
	 * the tree to use for testing
	 */
	private BSTree<Integer> tree;
	private StringVisitor<Integer> strVisitor;
	// returns a tree loaded with the given items
	static BSTree<Integer> load(Integer... items)
	{
		StringVisitor<Integer> strVisitor = new StringVisitor<Integer>();
		IntComparator compare = new IntComparator();
		BSTree<Integer> tree = new BSTree<Integer>(compare);
		for (Integer value : items) {
			tree.add(value);
		}
		return tree;
	}

	@Test
	public void test_add()
	{
		// testing empty
		tree = load();
		tree.add(1);
		assertEquals( tree.toString(), "[1]" );

		// testing single
		tree = load(1);
		tree.add(2);
		assertEquals( tree.toString(), "[1 2]" );

		tree = load(1);
		tree.add(0);
		assertEquals( tree.toString(), "[1 0]" );

		// testing multi

		//far left
		tree = load( 10, 6, 16, 3, 7, 12, 17);
		tree.add(2);
		assertEquals( tree.toString(), "[10 6 16 3 7 12 17 2]" );

		//mid right
		tree = load( 10, 6, 16, 3, 7, 12, 17);
		tree.add(8);
		assertEquals( tree.toString(), "[10 6 16 3 7 12 17 8]" );

		//mid left
		tree = load( 10, 6, 16, 3, 7, 12, 17);
		tree.add(11);
		assertEquals( tree.toString(), "[10 6 16 3 7 12 17 11]" );

		//far right 
		tree = load( 10, 6, 16, 3, 7, 12, 17);
		tree.add(18);
		assertEquals( tree.toString(), "[10 6 16 3 7 12 17 18]" );
	}

	@Test
	public void test_addLoop()
	{
		// testing empty
		tree = load();
		tree.addLoop(1);
		assertEquals( tree.toString(), "[1]" );

		// testing single
		tree = load(1);
		tree.addLoop(2);
		assertEquals( tree.toString(), "[1 2]" );

		tree = load(1);
		tree.addLoop(0);
		assertEquals( tree.toString(), "[1 0]" );

		// testing multi

		//far left
		tree = load( 10, 6, 16, 3, 7, 12, 17);
		tree.addLoop(2);
		assertEquals( tree.toString(), "[10 6 16 3 7 12 17 2]" );

		//mid right
		tree = load( 10, 6, 16, 3, 7, 12, 17);
		tree.addLoop(8);
		assertEquals( tree.toString(), "[10 6 16 3 7 12 17 8]" );

		//mid left
		tree = load( 10, 6, 16, 3, 7, 12, 17);
		tree.addLoop(11);
		assertEquals( tree.toString(), "[10 6 16 3 7 12 17 11]" );

		//far right 
		tree = load( 10, 6, 16, 3, 7, 12, 17);
		tree.addLoop(18);
		assertEquals( tree.toString(), "[10 6 16 3 7 12 17 18]" );

		//far right 
		tree = load( 7, 5, 4);
		tree.addLoop(6);
		assertEquals( tree.toString(), "[7 5 4 6]" );
	}

	@Test
	public void test_isEmpty()
	{
		// testing empty
		tree = load();
		assertTrue(tree.isEmpty());
		assertEquals( tree.toString(), "[]" );

		// testing single
		tree = load(1);
		assertFalse(tree.isEmpty());
		assertEquals( tree.toString(), "[1]" );

		// testing multi
		tree = load( 7, 5, 9, 4, 6, 8, 10);
		assertFalse(tree.isEmpty());
		assertEquals( tree.toString(), "[7 5 9 4 6 8 10]" );

	}

	@Test
	public void test_maxValueLoop()
	{
		// testing empty
		tree = load();
		try { tree.maxValueLoop(); fail(); }
		catch (NoSuchElementException e) { /* test passed */ }	
		assertEquals( tree.toString(), "[]" );

		// testing single
		tree = load(1);
		assertTrue( tree.maxValueLoop() == 1 );
		assertEquals( tree.toString(), "[1]" );

		// testing multi
		tree = load( 7, 5, 9, 4, 6, 8, 10);
		assertTrue( tree.maxValueLoop() ==  10 );
		assertEquals( tree.toString(), "[7 5 9 4 6 8 10]" );

		tree = load( 10, 5, 4, 7);
		assertTrue( tree.maxValueLoop() ==  10 );
		assertEquals( tree.toString(), "[10 5 4 7]" );

	}

	@Test
	public void test_minValueLoop()
	{
		// testing empty
		tree = load();
		try { tree.minValueLoop(); fail(); }
		catch (NoSuchElementException e) { /* test passed */ }	
		assertEquals( tree.toString(), "[]" );

		// testing single
		tree = load(1);
		assertTrue( tree.minValueLoop() == 1 );
		assertEquals( tree.toString(), "[1]" );

		// testing multi
		tree = load( 7, 5, 9, 4, 6, 8, 10);
		assertTrue( tree.minValueLoop() ==  4 );
		assertEquals( tree.toString(), "[7 5 9 4 6 8 10]" );

		tree = load( 10, 5, 4, 7);
		assertTrue( tree.minValueLoop() ==  4 );
		assertEquals( tree.toString(), "[10 5 4 7]" );
	}

	@Test
	public void test_containsLoop()
	{
		// testing empty
		tree = load();
		assertFalse( tree.containsLoop(4));
		assertEquals( tree.toString(), "[]" );

		// testing single	
		tree = load(1);
		assertFalse( tree.containsLoop(4));
		assertEquals( tree.toString(), "[1]" );

		tree = load(1);
		assertTrue( tree.containsLoop(1));
		assertEquals( tree.toString(), "[1]" );

		// testing multi

		//false
		tree = load( 10, 6, 16, 3, 7, 12, 17);
		assertFalse( tree.containsLoop(4));
		assertEquals( tree.toString(), "[10 6 16 3 7 12 17]" );

		//root
		tree = load( 10, 6, 16, 3, 7, 12, 17);
		assertTrue( tree.containsLoop(10));
		assertEquals( tree.toString(), "[10 6 16 3 7 12 17]" );

		//far right
		tree = load( 10, 6, 16, 3, 7, 12, 17);
		assertTrue( tree.containsLoop(17));
		assertEquals( tree.toString(), "[10 6 16 3 7 12 17]" );

		//far Left 
		tree = load( 10, 6, 16, 3, 7, 12, 17);
		assertTrue( tree.containsLoop(3));
		assertEquals( tree.toString(), "[10 6 16 3 7 12 17]" );

		//Left 
		tree = load( 10, 6, 16, 3, 7, 12, 17);
		assertTrue( tree.containsLoop(6));
		assertEquals( tree.toString(), "[10 6 16 3 7 12 17]" );
	}

	@Test
	public void test_maxValue()
	{
		// testing empty
		tree = load();
		try { tree.maxValue(); fail(); }
		catch (NoSuchElementException e) { /* test passed */ }	
		assertEquals( tree.toString(), "[]" );

		// testing single
		tree = load(1);
		assertTrue( tree.maxValue() == 1 );
		assertEquals( tree.toString(), "[1]" );

		// testing multi
		tree = load( 7, 5, 9, 4, 6, 8, 10);
		assertTrue( tree.maxValue() ==  10 );
		assertEquals( tree.toString(), "[7 5 9 4 6 8 10]" );

		tree = load( 10, 5, 4, 7);
		assertTrue( tree.maxValue() ==  10 );
		assertEquals( tree.toString(), "[10 5 4 7]" );

	}

	@Test
	public void test_minValue()
	{
		// testing empty
		tree = load();
		try { tree.minValue(); fail(); }
		catch (NoSuchElementException e) { /* test passed */ }	
		assertEquals( tree.toString(), "[]" );

		// testing single
		tree = load(1);
		assertTrue( tree.minValue() == 1 );
		assertEquals( tree.toString(), "[1]" );

		// testing multi
		tree = load( 7, 5, 9, 4, 6, 8, 10);
		assertTrue( tree.minValue() ==  4 );
		assertEquals( tree.toString(), "[7 5 9 4 6 8 10]" );

		tree = load( 10, 5, 4, 7);
		assertTrue( tree.minValue() ==  4 );
		assertEquals( tree.toString(), "[10 5 4 7]" );
	}

	@Test
	public void test_contains()
	{
		// testing empty
		tree = load();
		assertFalse( tree.contains(4));
		assertEquals( tree.toString(), "[]" );

		// testing single	
		tree = load(1);
		assertFalse( tree.contains(4));
		assertEquals( tree.toString(), "[1]" );

		tree = load(1);
		assertTrue( tree.contains(1));
		assertEquals( tree.toString(), "[1]" );

		// testing multi

		//false
		tree = load( 10, 6, 16, 3, 7, 12, 17);
		assertFalse( tree.contains(4));
		assertEquals( tree.toString(), "[10 6 16 3 7 12 17]" );

		//root
		tree = load( 10, 6, 16, 3, 7, 12, 17);
		assertTrue( tree.contains(10));
		assertEquals( tree.toString(), "[10 6 16 3 7 12 17]" );

		//far right
		tree = load( 10, 6, 16, 3, 7, 12, 17);
		assertTrue( tree.contains(17));
		assertEquals( tree.toString(), "[10 6 16 3 7 12 17]" );

		//far Left 
		tree = load( 10, 6, 16, 3, 7, 12, 17);
		assertTrue( tree.contains(3));
		assertEquals( tree.toString(), "[10 6 16 3 7 12 17]" );

		//Left 
		tree = load( 10, 6, 16, 3, 7, 12, 17);
		assertTrue( tree.contains(6));
		assertEquals( tree.toString(), "[10 6 16 3 7 12 17]" );
	}

	@Test
	public void test_remove()
	{
		// testing empty
		tree = load();
		assertFalse( tree.remove(4));
		assertEquals( tree.toString(), "[]" );

		// testing single	
		tree = load(1);
		assertFalse( tree.remove(4));
		assertEquals( tree.toString(), "[1]" );

		tree = load(1);
		assertTrue( tree.remove(1));
		assertEquals( tree.toString(), "[]" );
		// testing multi
		//false
		tree = load( 10, 6, 16, 3, 7, 12, 17);
		assertFalse( tree.remove(4));
		assertEquals( tree.toString(), "[10 6 16 3 7 12 17]" );

		//root HasBoth
		tree = load( 10, 6, 16, 3, 7, 12, 17);
		assertTrue( tree.remove(10));
		assertEquals( tree.toString(), "[7 6 16 3 12 17]" );

		//far right //remove Missing
		tree = load( 10, 6, 16, 3, 7, 12, 17);
		assertTrue( tree.remove(17));
		assertEquals( tree.toString(), "[10 6 16 3 7 12]" );

		//far Left //remove Missing
		tree = load( 10, 6, 16, 3, 7, 12, 17);
		assertTrue( tree.remove(3));
		assertEquals( tree.toString(), "[10 6 16 7 12 17]" );

		//Left Child
		tree = load( 10, 6, 16, 3, 7, 12, 17);
		assertTrue( tree.remove(6));
		assertEquals( tree.toString(), "[10 3 16 7 12 17]" );

		//Remove Missing
		tree = load( 10, 6, 16, 3, 7, 12); //remove right w- left child
		assertTrue( tree.remove(16));
		assertEquals( tree.toString(), "[10 6 12 3 7]" );
		tree = load( 10, 6, 16, 3, 7, 17); //remove right w- left child
		assertTrue( tree.remove(16));
		assertEquals( tree.toString(), "[10 6 17 3 7]" );

		//remove Has Both
		tree = load( 10, 6, 16, 3, 7, 12, 17);
		assertTrue( tree.remove(6));
		assertEquals( tree.toString(), "[10 3 16 7 12 17]" );

		//remove Special Case
		tree = load( 10, 6, 16, 3, 7, 12, 17, 5, 4);   //rightmost child of left most child of removed  	
		assertTrue( tree.remove(3));
		assertEquals( tree.toString(), "[10 6 16 5 7 12 17 4]" );

		//remove Special Case
		tree = load( 10, 5);   	
		assertTrue( tree.remove(10));
		assertEquals( tree.toString(), "[5]" );

		tree = load( 10, 12);   	
		assertTrue( tree.remove(10));
		assertEquals( tree.toString(), "[12]" );

		tree = load( 10, 5, 15, 6);   	
		assertTrue( tree.remove(5));
		assertEquals( tree.toString(), "[10 6 15]" );

		tree = load( 10, 5, 15, 4, 6 ,3);   	
		assertTrue( tree.remove(5));
		assertEquals( tree.toString(), "[10 4 15 3 6]" );
	}	
	@Test
	public void test_preorder()
	{
		//empty
		tree = load();
		strVisitor = new StringVisitor<Integer>();
		tree.preorder( strVisitor );
		assertEquals( strVisitor.getValue(), "[]" );
		assertEquals( tree.toString(), "[]" );
		assertEquals( tree.toStringPre(), "[]" );
		
		//single
		tree = load( 10 );
		strVisitor = new StringVisitor<Integer>();
		tree.preorder( strVisitor );
		assertEquals( strVisitor.getValue(), "[10]" );
		assertEquals( tree.toString(), "[10]" );
		assertEquals( tree.toStringPre(), "[10]" );

		//multi
		tree = load( 10, 6, 16, 3, 7, 12, 17 );
		strVisitor = new StringVisitor<Integer>();
		tree.preorder( strVisitor );
		assertEquals( strVisitor.getValue(), "[10 6 3 7 16 12 17]" );
		assertEquals( tree.toString(), "[10 6 16 3 7 12 17]" );
		assertEquals( tree.toStringPre(), "[10 6 3 7 16 12 17]" );
	}
	
	@Test
	public void test_inorder()
	{
		//empty
		tree = load();
		strVisitor = new StringVisitor<Integer>();
		tree.inorder( strVisitor );
		assertEquals( strVisitor.getValue(), "[]" );
		assertEquals( tree.toString(), "[]" );
		assertEquals( tree.toStringPre(), "[]" );
		
		//single
		tree = load( 10 );
		strVisitor = new StringVisitor<Integer>();
		tree.inorder( strVisitor );
		assertEquals( strVisitor.getValue(), "[10]" );
		assertEquals( tree.toString(), "[10]" );
		assertEquals( tree.toStringPre(), "[10]" );

		//multi
		tree = load( 10, 6, 16, 3, 7, 12, 17 );
		strVisitor = new StringVisitor<Integer>();
		tree.inorder( strVisitor );
		assertEquals( strVisitor.getValue(), "[3 6 7 10 12 16 17]" );
		assertEquals( tree.toString(), "[10 6 16 3 7 12 17]" );
		assertEquals( tree.toStringPre(), "[10 6 3 7 16 12 17]" );
	}
	
	@Test
	public void test_postorder()
	{
		//empty
		tree = load();
		strVisitor = new StringVisitor<Integer>();
		tree.postorder( strVisitor );
		assertEquals( strVisitor.getValue(), "[]" );
		assertEquals( tree.toString(), "[]" );
	//	assertEquals( tree.toStringPre(), "[]" );
		
		//single
		tree = load( 10 );
		strVisitor = new StringVisitor<Integer>();
		tree.postorder( strVisitor );
		assertEquals( strVisitor.getValue(), "[10]" );
		assertEquals( tree.toString(), "[10]" );
		assertEquals( tree.toStringPre(), "[10]" );

		//multi
		tree = load( 10, 6, 16, 3, 7, 12, 17 );
		strVisitor = new StringVisitor<Integer>();
		tree.postorder( strVisitor );
		assertEquals( strVisitor.getValue(), "[3 7 6 12 17 16 10]" );
		assertEquals( tree.toString(), "[10 6 16 3 7 12 17]" );
		assertEquals( tree.toStringPre(), "[10 6 3 7 16 12 17]" );
	}
	
	@Test
	public void test_equals()
	{
		// self
		tree = load();//empty
		assertTrue( tree.equals(tree));
		assertEquals( tree.toString(), "[]" );
		assertEquals( tree.toStringPre(), "[]" );

		tree = load( 1 ); //single
		assertTrue( tree.equals(tree));
		assertEquals( tree.toString(), "[1]" );
		assertEquals( tree.toStringPre(), "[1]" );

		tree = load( 10, 6, 16, 3, 7, 12, 17 ); //multi
		assertTrue( tree.equals(tree));
		assertEquals( tree.toString(), "[10 6 16 3 7 12 17]" );
		assertEquals( tree.toStringPre(), "[10 6 3 7 16 12 17]" );

		// twin
		tree = load();//empty
		assertTrue( tree.equals(load()));
		assertEquals( tree.toString(), "[]" );
		assertEquals( tree.toStringPre(), "[]" );

		tree = load( 1 );//single
		assertTrue( tree.equals(load( 1 )));
		assertEquals( tree.toString(), "[1]" );
		assertEquals( tree.toStringPre(), "[1]" );

		tree = load( 10, 6, 16, 3, 7, 12, 17 ); //multi
		assertTrue( tree.equals(load( 10, 6, 16, 3, 7, 12, 17)));
		assertEquals( tree.toString(), "[10 6 16 3 7 12 17]" );
		assertEquals( tree.toStringPre(), "[10 6 3 7 16 12 17]" );

		// different
		tree = load();//empty
		assertFalse( tree.equals(load(2)));
		assertEquals( tree.toString(), "[]" );
		assertEquals( tree.toStringPre(), "[]" );

		tree = load( 1 );//single
		assertFalse( tree.equals(load( 2 )));
		assertEquals( tree.toString(), "[1]" );
		assertEquals( tree.toStringPre(), "[1]" );

		tree = load( 10, 6, 16, 3, 7, 12, 17 ); //multi
		assertTrue( tree.equals(load(10, 16, 6, 7, 3, 12, 17)));
		assertEquals( tree.toString(), "[10 6 16 3 7 12 17]" );
		assertEquals( tree.toStringPre(), "[10 6 3 7 16 12 17]" );
	}
	
	@Test
	public void test_clone()
	{
//		tree = load(  );
//		try { tree.clone(); fail(); }
//		catch (CloneNotSupportedException e) { /* test passed */ }
//		assertEquals( tree.toString(), "[10 6 16 3 7 12 17]" );
//		assertEquals( tree.toStringPre(), "[10 6 3 7 16 12 17]" );
		
		// cloning
		tree = load();//empty
		assertTrue(tree.clone().equals(tree));
		assertEquals( tree.toString(), "[]" );
		assertEquals( tree.toStringPre(), "[]" );

		tree = load( 1 );//single
		assertTrue(tree.clone().equals(tree));
		assertEquals( tree.toString(), "[1]" );
		assertEquals( tree.toStringPre(), "[1]" );

		tree = load( 10, 6, 16, 3, 7, 12, 17 ); //multi
		assertTrue(tree.clone().equals(tree));
		assertEquals( tree.toString(), "[10 6 16 3 7 12 17]" );
		assertEquals( tree.toStringPre(), "[10 6 3 7 16 12 17]" );
	}
	@Test
	public void test_BSTree()
	{
		Integer[] empty = {};
		//Create the tree with the new constructor and the numbers (not with load())
		IntComparator compare = new IntComparator();
		BSTree<Integer> preTree = new BSTree<Integer>(empty, compare);
		//Assert that toString() and toStringPre() produce the expected result.
		assertEquals( preTree.toString(), "[]" );
		assertEquals( preTree.toStringPre(), "[]" );

		Integer[] single = {10};
//		BSTree<Integer> singleTree = new BSTree<Integer>(single, compare);
//		assertEquals( singleTree.toString(), "[10]" );
//		assertEquals( singleTree.toStringPre(), "[10]" );
//
//		Integer[] multiple = {10,6, 3, 7, 16, 12, 17};
//		BSTree<Integer> multipleTree = new BSTree<Integer>(multiple, compare);
//		assertEquals( multipleTree.toString(), "[10 6 16 3 7 12 17]" );
//		assertEquals( multipleTree.toStringPre(), "[10 6 3 7 16 12 17]" );
	}
	
	
}