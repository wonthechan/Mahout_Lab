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
		
		/* ������ �� ���� */
		DataModel model = new FileDataModel(new File("src/dataset.csv"));
		
		/* ���絵 �� ���� */
		UserSimilarity similarity = new PearsonCorrelationSimilarity(model);
		
		/* ��� ������κ��� �־��� ������ Ư�� �Ӱ谪�� �����ϰų� �ʰ��ϴ� neighborhood ���� */
		UserNeighborhood neighborhood = new ThresholdUserNeighborhood(0.1,similarity, model);
		
		/* ����� ��õ�� ���� */
		UserBasedRecommender recommender = new GenericUserBasedRecommender(model, neighborhood, similarity);
		
		/* ID�� 2���� ������� 3���� �������� ��õ */
		List<RecommendedItem> recommendations = recommender.recommend(2, 3);
		for (RecommendedItem recommendation : recommendations) {
			System.out.println(recommendation);
		}
	}

}
