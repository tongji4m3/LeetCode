```
设计并实现最近最少使用（LRU）缓存的数据结构。它应该支持以下操作：get和put。
get(key) - 如果key存在于缓存中，则获取key的值（将始终为正），否则返回-1。
put(key, value) - 如果key尚不存在，则设置或插入值。当缓存达到其容量时，它应该在插入新项之前使最近最少使用的项无效。

跟进：
你能否在O（1）时间复杂度下进行这两项操作？

Example:
LRUCache cache = new LRUCache( 2 /* capacity */ );
cache.put(1, 1);
cache.put(2, 2);
cache.get(1);       // returns 1
cache.put(3, 3);    // evicts key 2
cache.get(2);       // returns -1 (not found)
cache.put(4, 4);    // evicts key 1
cache.get(1);       // returns -1 (not found)
cache.get(3);       // returns 3
cache.get(4);       // returns 4
```

```c++
struct Node 
{
	int _key,_value;
	Node * _next,* _prev;
	Node(int key, int value, Node* prev = NULL, Node* next = NULL) :_key(key), _value(value), _prev(prev), _next(next) {}
};

class LRUCache 
{
public:
	LRUCache(int capacity) :_capacity(capacity), _head(NULL), _tail(NULL) {}

	int get(int key) 
	{
		if (_nums.find(key) != _nums.end()) 
		{
			Node* currentNode = _nums[key];
			remove(currentNode);
			add(currentNode);
			return currentNode->_value;
		}
		return -1;
	}

	void put(int key, int value) 
	{
		if (_nums.find(key) != _nums.end()) 
		{
			Node* currentNode = _nums[key];
			currentNode->_value = value;
			remove(currentNode);
			add(currentNode);
		}
		else 
		{
			if (_nums.size() == _capacity) 
			{
				Node* nodeToRemove = _head;
				_nums.erase(nodeToRemove->_key);
				remove(_head);
			}
			Node* currentNode = new Node(key, value,_tail);
			if (_nums.size() == 0)
				_head = currentNode;
			else
				_tail->_next = currentNode;
			_tail = currentNode;
			_nums[key] = currentNode;
		}
	}

private:
	unordered_map<int, Node*> _nums;
	Node* _head,* _tail;
	int _capacity;
	void remove(Node* node) 
	{
		Node* prevNode = node->_prev;
		Node* nextNode = node->_next;
		node->_next = NULL;
		if (prevNode)
			prevNode->_next = nextNode;
		else
			_head = nextNode;

		if (nextNode)
			nextNode->_prev = prevNode;
		else
			_tail = prevNode;
	}
	void add(Node* node) 
	{
		if (_tail)
			_tail->_next = node;
		else
			_head = node;
		node->_prev = _tail;
		_tail = node;
	}
};
```
