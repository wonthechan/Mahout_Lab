import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.common.LongPrimitiveIterator;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.recommender.GenericItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;

public class ItemRecommend {
	// Item Based Recommend
	public static void main(String[] args) {

		DataModel model;
		
		try {
			/* ������ �� ���� */
			model = new FileDataModel(new File("src/movies.csv"));
			
			/* ���絵 �� ���� */
			ItemSimilarity similarity = new PearsonCorrelationSimilarity(model);
			
			/* ��õ�� ���� : ItemBased */
			GenericItemBasedRecommender recommender = new GenericItemBasedRecommender(model, similarity);
			
			int x = 1;
			/* ������ �� ���� item���� iterator�� �ܰ躰 �̵��ϸ� ��õ �����۵� ���� */
			for(LongPrimitiveIterator items = model.getItemIDs(); items.hasNext();) {
				
				long itemID = items.nextLong(); /* ���� item ID */
				
				/* ���� item ID�� ���� ������ 5�� ������ ��õ */
				List<RecommendedItem> recommendations = recommender.mostSimilarItems(itemID, 5);
				
				/* ������ ������ ��� = "���� ������ ID | ��õ�� ������ ID | ���絵" */
				for(RecommendedItem recommendation : recommendations) {
					System.out.println(itemID + ", " + recommendation.getItemID() + ", " + recommendation.getValue());
					//System.out.println(recommendation);
				}
				x++; /* ������ ID 5���� ������ �����۵� 5���� */
				if(x>5) System.exit(0);
			}
			
		} catch(IOException | TasteException e) {
			e.printStackTrace();
		}
		
	}

}
