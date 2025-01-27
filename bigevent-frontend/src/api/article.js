import request from '@/utils/request';
import { useTokenStore } from '@/stores/token';

export const articleCategoryListService = () => {
    const tokenStore = useTokenStore();
    return request.get('/category', {
        headers: {
            Authorization: tokenStore.token
        }
    });
}