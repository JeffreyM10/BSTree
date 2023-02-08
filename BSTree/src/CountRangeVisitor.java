import java.util.Comparator;

public class CountRangeVisitor<E> implements Visitor<E>
{
	int counter;
	E itemA;
	E itemB;
	Comparator<E> comp;
	public CountRangeVisitor(E a, E b, Comparator<E> comp)
	{
		this.comp = comp;
		itemA = a;
		itemB = b;
		counter = 0;
	}
	
	@Override
	public void visit(E item)
	{
		if( comp.compare(item, itemA) >= 0 && comp.compare(item, itemB) <= 0 ){
			counter++;
		}
	}
	
	public int countVisit()
	{
		return counter;
	}
}
