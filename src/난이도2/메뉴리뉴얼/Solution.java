package 난이도2.메뉴리뉴얼;

import java.util.*;

public class Solution {
    public static String[] solution(String[] orders, int[] course) {
        Map<String, Integer> map = new HashMap<>();
        for (String order : orders) {
            order = sortString(order);
            dfs(new boolean[order.length()], order, 0, course, map);
        }
        List<String> keySetList = new ArrayList<>(map.keySet());
        for (int i = 0; i < keySetList.size(); i++) {
            String key = keySetList.get(i);
            if (map.get(key) < 2) {
                keySetList.remove(i--);
                continue;
            }
            for (int courseLength : course) {
                if (key.length() != courseLength) {
                    continue;
                }
                for (int j = 0; j < i; j++) {
                    String preKey = keySetList.get(j);
                    if (key.length() != preKey.length()) {
                        continue;
                    }
                    if (map.get(key) < map.get(preKey)) {
                        keySetList.remove(i--);
                        break;
                    } else if (map.get(key) > map.get(preKey)) {
                        keySetList.remove(j--);
                        i--;
                    }
                }
            }
        }
        Collections.sort(keySetList);
        return keySetList.toArray(new String[0]);
    }

    public static void dfs(boolean[] visited, String order, int depth, int[] course,
                    Map<String, Integer> map) {
        if (depth == order.length()) {
            String menu = "";
            for (int i = 0; i < order.length(); i++) {
                if (visited[i]) {
                    menu += order.charAt(i);
                }
            }
            if (menu.length() < 2) {
                return;
            }
            for (int courseLength : course) {
                if (menu.length() == courseLength) {
                    if (!map.containsKey(menu)) {
                        map.put(menu, 1);
                    } else {
                        map.replace(menu, map.get(menu) + 1);
                    }
                }
            }
        } else {
            visited[depth] = true;
            dfs(visited, order, depth + 1, course, map);
            visited[depth] = false;
            dfs(visited, order, depth + 1, course, map);
        }
    }

    public static String sortString(String string) {
        char[] charArray = string.toCharArray();
        Arrays.sort(charArray);
        return String.valueOf(charArray);
    }

    public static void main(String[] args) {
        String[] orders = {"ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH"};
        int[] course = {2, 3, 4};
        String[] result = {"AC", "ACDE", "BCFG", "CDE"};
        if (Arrays.equals(solution(orders, course), result)) {
            System.out.println("통과했습니다.");
        } else {
            System.out.println("불일치했습니다.");
        }
    }
}