import pandas as pd
import math
from os.path import dirname, join

def tokenize(message):
    return message.split()

def count_words(training_set):
    counts = {}
    for message, label in training_set:
        for word in tokenize(message):
            if label not in counts:
                counts[label] = {}
            if word not in counts[label]:
                counts[label][word] = 0
            counts[label][word] += 1
    return counts

def word_probabilities(counts, total_spams, total_non_spams, k=0.5):
    probabilities = {}
    for label in counts.keys():
        probabilities[label] = {}
        total_words = sum(counts[label].values())
        for word in counts[label]:
            probabilities[label][word] = (counts[label][word] + k) / (total_words + k * (total_spams + total_non_spams))
    return probabilities

def spam_probability(word_probs, message):
    message_words = tokenize(message)
    log_prob_if_spam = log_prob_if_not_spam = 0.0
    for word, prob_if_spam, prob_if_not_spam in word_probs:
        if word in message_words:
            log_prob_if_spam += math.log(prob_if_spam)
            log_prob_if_not_spam += math.log(prob_if_not_spam)
        else:
            log_prob_if_spam += math.log(1.0 - prob_if_spam)
            log_prob_if_not_spam += math.log(1.0 - prob_if_not_spam)
    prob_if_spam = math.exp(log_prob_if_spam)
    prob_if_not_spam = math.exp(log_prob_if_not_spam)
    return prob_if_spam / (prob_if_spam + prob_if_not_spam)

def get_predictions(word_probs, messages):
    return [(message, spam_probability(word_probs, message)) for message in messages]

filename = join(dirname(__file__), "dataset.txt")
with open(filename, 'r', encoding='utf-8') as f:
    messages = [line.strip().split('\t') for line in f.readlines()]

df = pd.DataFrame(messages, columns=['label', 'message'])

# Convert the labels to binary values (0 for 'ham', 1 for 'spam')
df['label'] = df['label'].map({'ham': 0, 'spam': 1})

# Split the dataset into training and testing sets
train_data = df.sample(frac=0.8, random_state=1)
test_data = df.drop(train_data.index)

# Count the occurrences of each word in spam and non-spam messages
counts = count_words([(message, label) for message, label in zip(train_data['message'], train_data['label'])])

# Calculate the probabilities of each word given that a message is spam or not spam
word_probs = []
num_spams = sum(train_data['label'])
num_non_spams = len(train_data) - num_spams
for word in counts[0]:
    if word in counts[1]:
        prob_if_spam = (counts[1][word] + 0.5) / (num_spams + 1)
        prob_if_not_spam = (counts[0][word] + 0.5) / (num_non_spams + 1)
        word_probs.append((word, prob_if_spam, prob_if_not_spam))

# Test the classifier on the testing set and print the accuracy
# Calculate the accuracy of the classifier on the testing set
correct_predictions = 0
for message, label in zip(test_data['message'], test_data['label']):
    probability = spam_probability(word_probs, message)
    if (probability >= 0.5 and label == 1) or (probability < 0.5 and label == 0):
        correct_predictions += 1
accuracy = correct_predictions / len(test_data)
print(f'Accuracy: {accuracy:.2f}')


def main(message):
    #Use trained classifier to predict message.
    probability = spam_probability(word_probs, message)
    print(message)
    if probability >= 0.5:
        return "The message is fraudulent."
    else:
        return "The message is legitimate."


