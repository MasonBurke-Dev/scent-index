import pandas as pd

cols_to_keep = ['Brand', 'Country', 'Ge']

df = pd.read_csv('/Users/mason/IdeaProjects/cologne_app/src/main/resources/data/cleaned-data.csv', sep=';', encoding="utf-8",on_bad_lines="skip")

def finder(url):
    last = str(url).split("-")
    return last[-1].split(".")[0]

row = "https://www.fragrantica.com/perfume/jean-paul-gaultier/le-male-pride-2024-90393.html;"


for name in df.columns:
    print(name)


# df = df[df["Gender"].str.strip().str.lower() != "women"]

for x in df.index:
    holder = finder(str(df.loc[x,"url"]))
    df.loc[x,"url"] = holder

    



# Save the result back to CSV
df.to_csv('/Users/mason/IdeaProjects/cologne_app/src/main/resources/data/cleaned-data.csv', index=False, sep=';')

# for word in (df.columns[0].split(';')):
#     print(word + ",    ")

# https://www.fragrantica.com/perfume/jean-paul-gaultier/le-male-pride-2024-90393.html;le-male-pride-2024;jean-paul-gaultier;France;men;1,95;285;2024;citruses
# https://www.fragrantica.com/perfume/Jean-Paul-Gaultier/Le-Male-pride-2024-90393.html



