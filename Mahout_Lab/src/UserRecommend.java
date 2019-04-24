import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.ThresholdUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.UserBasedRecommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

public class UserRecommend {
	// User Based Recommend
	public static void main(String[] args) throws IOException, TasteException {
		
		/* 데이터 모델 생성 */
		DataModel model = new FileDataModel(new File("src/dataset.csv"));
		
		/* 유사도 모델 생성 */
		UserSimilarity similarity = new PearsonCorrelationSimilarity(model);
		
		/* 모든 유저들로부터 주어진 유저와 특정 임계값을 충족하거나 초과하는 neighborhood 기준 */
		UserNeighborhood neighborhood = new ThresholdUserNeighborhood(0.1,similarity, model);
		
		/* 사용자 추천기 생성 */
		UserBasedRecommender recommender = new GenericUserBasedRecommender(model, neighborhood, similarity);
		
		/* ID가 2번인 사람에게 3개의 아이템을 추천 */
		List<RecommendedItem> recommendations = recommender.recommend(2, 3);
		for (RecommendedItem recommendation : recommendations) {
			System.out.println(recommendation);
		}
	}

}
