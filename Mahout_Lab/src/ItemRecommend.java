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
			/* 데이터 모델 생성 */
			model = new FileDataModel(new File("src/movies.csv"));
			
			/* 유사도 모델 생성 */
			ItemSimilarity similarity = new PearsonCorrelationSimilarity(model);
			
			/* 추천기 선택 : ItemBased */
			GenericItemBasedRecommender recommender = new GenericItemBasedRecommender(model, similarity);
			
			int x = 1;
			/* 데이터 모델 내의 item들의 iterator를 단계별 이동하며 추천 아이템들 제공 */
			for(LongPrimitiveIterator items = model.getItemIDs(); items.hasNext();) {
				
				long itemID = items.nextLong(); /* 현재 item ID */
				
				/* 현재 item ID와 가장 유사한 5개 아이템 추천 */
				List<RecommendedItem> recommendations = recommender.mostSimilarItems(itemID, 5);
				
				/* 유사한 아이템 출력 = "현재 아이템 ID | 추천된 아이템 ID | 유사도" */
				for(RecommendedItem recommendation : recommendations) {
					System.out.println(itemID + ", " + recommendation.getItemID() + ", " + recommendation.getValue());
					//System.out.println(recommendation);
				}
				x++; /* 아이템 ID 5까지 유사한 아이템들 5개씩 */
				if(x>5) System.exit(0);
			}
			
		} catch(IOException | TasteException e) {
			e.printStackTrace();
		}
		
	}

}
