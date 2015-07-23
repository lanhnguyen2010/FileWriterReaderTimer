import java.io.Closeable;
import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;

/**
 * Created by lanhnguyen on 7/22/2015.
 */
public class CustomScanner implements Iterator<String>, Closeable {

    private Reader _reader;
    private char _delimiter;
    private int _matcher;
    private char[] _input;
    private int _bufferSize;
    private int _currentPos = 0;
    private int _endOfCurrentBuf = 0;
    private boolean _hasNext = true;
    char[] _newInput;
    private int _read = 0;


    public CustomScanner(Reader _reader, char _delimiter, int _bufferSize) throws IOException {
        this._reader = _reader;
        this._delimiter = _delimiter;
        this._bufferSize = _bufferSize;
        this._input = new char[_bufferSize];
        findNextMatcher();
    }

    private void findNextMatcher() throws IOException {


        while (true) {
            if (_currentPos >= _endOfCurrentBuf) {

                //resize input array
                _newInput = new char[_currentPos + _bufferSize + 1];
                System.arraycopy(_input, 0, _newInput, 0, _currentPos);
                _input = _newInput;

                if (_reader.read(_input, _currentPos, _bufferSize) != -1) {
                    _endOfCurrentBuf += _bufferSize;
                } else {
                    _hasNext = false;
                    break;
                }
            }
            if (_currentPos < _input.length && _input[_currentPos] == _delimiter) {
                _matcher = _currentPos;
                break;
            }
            _currentPos++;
        }

    }

    @Override
    public boolean hasNext() {
        return _hasNext;
    }

    @Override
    public String next() {
        String s = charToString(_input, 0, _matcher - 1);
        _matcher = 0;
        _currentPos = 0;
        _input = new char[_bufferSize];
        _newInput = new char[0];
        _endOfCurrentBuf = 0;
        try {
            findNextMatcher();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return s;
    }

    private String charToString(char[] chars, int start, int end) {
        StringBuilder result = new StringBuilder();
        result.append(chars, start, end - start + 1);
        return result.toString();
    }

    @Override
    public void close() throws IOException {
        _reader.close();

    }
}
