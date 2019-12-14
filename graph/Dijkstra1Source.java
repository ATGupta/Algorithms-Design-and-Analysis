package graph;

import Main.NamedList;

public class Dijkstra1Source {
	
	Integer[] dist;
	
	public Dijkstra1Source(Integer[][] G, Integer source) {
		Integer n = G.length;
		dist = new Integer[n];
		Integer sum = 0;
		for(Integer i=0; i<n; i++) {
			for(Integer j=0; j<n; j++) {
				sum += G[i][j];
			}
		}
		
		Integer INF = sum + 1;
		
		for(Integer i=0; i<n; i++) dist[i] = INF;
		
		dijkistra(G, source);
	}

	private void dijkistra(Integer[][] g, Integer s) {
		Integer n = g.length;
		NamedList<Integer> q = new NamedList<Integer>(new Integer[]{s});
		
		dist[s] = 0;
		while(q.size()>0) {
			Integer v=q.dequeue();
			
			for(Integer i=0; i<n; i++) {
				if(g[v][i] != 0) {
					if(dist[v] + g[v][i] < dist[i]) {
						dist[i] = dist[v] + g[v][i];
						if(!q.containsValue(i)) q.enqueue(i);
					}
				}
			}
		}
	}
	
	public String toString() {
		NamedList<Integer> d=new NamedList<Integer>();
		Integer n = dist.length;
		
		for(Integer i=0; i<n; i++) {
			d.add(Integer.toString(i), dist[i]);
		}
		
		return d.toString();
	}
}
